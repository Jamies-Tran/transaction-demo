package com.example.transactionlogdemo.domain.service.execution.route;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;

public interface RouteExecutionService {
    Object execute(String routeCode, RequestDefinition requestDefinition, Retry retry);
}
