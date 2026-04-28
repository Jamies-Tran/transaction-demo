package com.example.transactionlogdemo.domain.service.execution.route;

import com.example.transactionlogdemo.domain.entity.execution.result.route.RouteExecutionResult;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;

public interface RouteExecutionService {
    RouteExecutionResult execute(String routeCode, RequestDefinition requestDefinition, Retry retry);
}
