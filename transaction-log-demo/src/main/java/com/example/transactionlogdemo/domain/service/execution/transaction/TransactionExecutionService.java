package com.example.transactionlogdemo.domain.service.execution.transaction;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;

import java.util.List;

public interface TransactionExecutionService {
    List<WorkflowExecutionResult.ExecutionResult> execute(List<String> transactionCodes, ExecutionContext context);
}
