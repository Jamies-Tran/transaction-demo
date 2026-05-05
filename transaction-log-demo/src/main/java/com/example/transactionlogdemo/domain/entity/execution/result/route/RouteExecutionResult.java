package com.example.transactionlogdemo.domain.entity.execution.result.route;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.enums.EnumTransactionResultStatus;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public record RouteExecutionResult(
        Object responseData,
        Boolean succeed,
        List<WorkflowExecutionResult.ExecutionLog> logs
) {
    public RouteExecutionResult {
        if (Objects.isNull(succeed)) {
            succeed = logs.stream().noneMatch(r -> Objects
                    .equals(r.status(), EnumTransactionResultStatus.ERROR.name()));
        }
    }
}
