package com.example.transactionlogdemo.application.dto.response.execution.result.workflow;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record WorkflowExecutionResultResponse(
        String id,
        String workflowCode,
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        LocalDateTime executeAt,
        String executionResult,
        List<ExecutionLogResponse> executionLogs
) {

    public record ExecutionLogResponse(
            String transactionId,
            String transactionCode,
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
                String body,
                List<Object> pathVariable
        ) {}
    }
}
