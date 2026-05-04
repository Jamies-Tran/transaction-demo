package com.example.transactionlogdemo.domain.entity.execution.result.workflow;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record WorkflowExecutionResult(
        String id,
        String workflowCode,
        LocalDateTime executeAt,
        List<ExecutionResult> executionResults
) {
    public WorkflowExecutionResult {
        if (Objects.isNull(executeAt)) {
            executeAt = LocalDateTime.now();
        }
    }

    @Builder
    public record ExecutionResult(
            String transactionId,
            String transactionCode,
            DataRequest dataRequest,
            String dataResult,
            Integer retryCount,
            Long requestMillis,
            Long responseMillis,
            String errMessage,
            String httpStatus,
            String status,
            String errSourceCode,
            String errSourceName
    ) {
        @Builder
        public record DataRequest(
                String params,
                String body,
                List<Object> pathVariable
        ) {}
    }
}
