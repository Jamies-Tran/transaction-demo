package com.example.transactionlogdemo.application.usecase.execution.transaction;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.execution.transaction.TransactionExecutionService;
import com.example.transactionlogdemo.domain.service.transaction.TransactionService;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.JsonPathUtils;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionExecutionUseCase implements TransactionExecutionService {
    TransactionService transactionService;

    RouteExecutionService routeExecutionService;

    @Override
    public void execute(List<String> transactionCodes, ExecutionContext context) {
        List<Transaction> transactions = transactionService.getAllByCodeIn(transactionCodes);
        for (Transaction t : transactions) {
            RequestDefinition requestDefinition = buildRequestDefinitionData(t, context);
            Object routeResponse = routeExecutionService.execute(t.routeCode(), requestDefinition, t.retry());
            mapResponse(t.responseSchema().body(), routeResponse, context);
        }
    }

    private void mapResponse(Map<String, Object> mapping, Object source, ExecutionContext context) {
        for (Map.Entry<String, Object> m : mapping.entrySet()) {
            String key = m.getKey();
            Object value = m.getValue();
            if (value instanceof String jsonPath) {
                Object valueMapped = JsonPathUtils.read(source, jsonPath);
                context.put(key, valueMapped);
            }
            if (value instanceof Map map) {
                mapResponse(map, source, context);
            }
        }
    }

    private RequestDefinition buildRequestDefinitionData(
            Transaction transaction,
            ExecutionContext context
    ) {
        Map<String, Object> mapParams = (Map<String, Object>) JsonPathUtils.mapJson(transaction.requestSchema().params(), context.getData());
        Map<String, Object> mapBody = (Map<String, Object>) JsonPathUtils.mapJson(transaction.requestSchema().body(), context.getData());
        Authentication authentication = transaction.authentication();

        return RequestDefinition.builder()
                .transactionId(transaction.id())
                .params(mapParams)
                .body(mapBody)
                .authentication(authentication)
                .build();

    }
}
