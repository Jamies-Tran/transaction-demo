package com.example.transactionlogdemo.application.dto.response.execution.result.workflow;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;

import java.time.LocalDateTime;
import java.util.List;

public record WorkflowExecutionResultResponse(
        String id,
        String workflowCode,
        LocalDateTime executeAt,
        List<ExecutionResultResponse> executionResults
) {

    public record ExecutionResultResponse(
            String transactionId,
            DataRequestResponse dataRequest,
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
        public record DataRequestResponse (
                String params,
                String body
        ) {}
    }
}
