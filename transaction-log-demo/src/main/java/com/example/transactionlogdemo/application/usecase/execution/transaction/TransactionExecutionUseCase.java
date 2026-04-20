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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionExecutionUseCase implements TransactionExecutionService {
    TransactionService transactionService;

    RouteExecutionService routeExecutionService;

    @Override
    public void execute(List<String> transactionCodes, ExecutionContext context) {
        List<Transaction> transactions = transactionService.getAllByCodeIn(transactionCodes);
        Map<String, RequestDefinition> requestDefinitionMap = buildRequestDefinitionData(transactions, context);
        requestDefinitionMap.forEach(routeExecutionService::execute);
    }

    private Map<String, RequestDefinition> buildRequestDefinitionData(
            List<Transaction> transactions,
            ExecutionContext context
    ) {
        return transactions.stream()
                .collect(Collectors.toMap(Transaction::routeCode, transaction -> {
                    Map<String, Object> mapParams = (Map<String, Object>) JsonPathUtils.mapJson(transaction.data().params(), context.getData());
                    Map<String, Object> mapBody = (Map<String, Object>) JsonPathUtils.mapJson(transaction.data().body(), context.getData());
                    Authentication authentication = transaction.authentication();

                    return RequestDefinition.builder()
                            .params(mapParams)
                            .body(mapBody)
                            .authentication(authentication)
                            .build();
                }));

    }
}
