package com.example.transactionlogdemo.application.usecase.execution.transaction;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.execution.result.route.RouteExecutionResult;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.enums.EnumExecutionErrorSource;
import com.example.transactionlogdemo.domain.enums.EnumTransactionResultStatus;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.JsonPathUtils;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("unchecked")
public class TransactionExecutionUseCase implements TransactionExecutionService {
    TransactionService transactionService;

    RouteExecutionService routeExecutionService;

    @Override
    public List<WorkflowExecutionResult.ExecutionResult> execute(
            List<Workflow.WorkflowTransaction> transactions,
            ExecutionContext context
    ) {
        LinkedList<Transaction> transactionByOrder = transactionByOrder(transactions);
        List<WorkflowExecutionResult.ExecutionResult> executionResults = new ArrayList<>();
        for (Transaction t : transactionByOrder) {
            RequestDefinition requestDefinition = buildRequestDefinitionData(t, context);
            RouteExecutionResult routeResponse = routeExecutionService.execute(t.routeCode(), requestDefinition,
                    t.retry());
            if (!routeResponse.succeed()) {
                break;
            }
            executionResults.addAll(routeResponse.results());
            mapResponse(t.responseSchema().body(), routeResponse.responseData(), context, executionResults);
        }

        return executionResults;
    }

    private void mapResponse(
            Map<String, Object> mapping,
            Object source,
            ExecutionContext context,
            List<WorkflowExecutionResult.ExecutionResult> results
    ) {
        for (Map.Entry<String, Object> m : mapping.entrySet()) {
            String key = m.getKey();
            Object value = m.getValue();
            if (value instanceof String jsonPath) {
                try {
                    Object valueMapped = JsonPathUtils.read(source, jsonPath);
                    context.put(key, valueMapped);
                } catch (Exception e) {
                    results.add(WorkflowExecutionResult.ExecutionResult.builder()
                            .errMessage(e.getMessage())
                            .dataResult(ObjectMapperUtils.convertToString(source))
                            .status(EnumTransactionResultStatus.ERROR.name())
                            .errSourceCode(EnumExecutionErrorSource.SYSTEM_ERR.getCode())
                            .errSourceName(EnumExecutionErrorSource.SYSTEM_ERR.getName())
                            .build());
                    return;
                }
            }
            if (value instanceof Map<?, ?> map) {
                mapResponse((Map<String, Object>) map, source, context, results);
            }
        }
    }

    private RequestDefinition buildRequestDefinitionData(
            Transaction transaction,
            ExecutionContext context
    ) {
        Map<String, Object> mapParams = (Map<String, Object>) JsonPathUtils.mapJson(transaction.requestSchema().params(), context.getData());
        Map<String, Object> mapBody = (Map<String, Object>) JsonPathUtils.mapJson(transaction.requestSchema().body(), context.getData());
        Transaction.Authentication authentication = transaction.authentication();

        return RequestDefinition.builder()
                .transactionId(transaction.id())
                .transactionCode(transaction.code())
                .params(mapParams)
                .body(mapBody)
                .authentication(authentication)
                .build();

    }

    private LinkedList<Transaction> transactionByOrder(List<Workflow.WorkflowTransaction> transactions) {
        List<String> transactionCodes = transactions.stream()
                .map(Workflow.WorkflowTransaction::transactionCode)
                .toList();
        Map<String, Transaction> transactionMap = transactionService.getAllByCodeIn(transactionCodes)
                .stream()
                .collect(Collectors.toMap(Transaction::code, Function.identity()));

        return transactions.stream()
                .sorted(Comparator.comparing(Workflow.WorkflowTransaction::executionOrder))
                .map(t -> transactionMap.get(t.transactionCode()))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
