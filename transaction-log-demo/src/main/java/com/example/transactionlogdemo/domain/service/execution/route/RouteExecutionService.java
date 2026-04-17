package com.example.transactionlogdemo.domain.service.execution.route;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;

public interface RouteExecutionService {
    void execute(String routeCode, ExecutionContext context);
}
