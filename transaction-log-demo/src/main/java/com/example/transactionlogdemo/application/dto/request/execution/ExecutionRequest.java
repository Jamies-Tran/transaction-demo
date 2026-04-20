package com.example.transactionlogdemo.application.dto.request.execution;

import java.util.Map;

public record ExecutionRequest(
        String workflowId,
        Map<String, Object> input
) {
}
