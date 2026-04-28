package com.example.transactionlogdemo.domain.entity.execution.result.route;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import lombok.Builder;

import java.util.List;

@Builder
public record RouteExecutionResult(
        Object responseData,
        List<WorkflowExecutionResult.ExecutionResult> results
) {
}
