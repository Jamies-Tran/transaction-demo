package com.example.transactionlogdemo.application.dto.request.execution;

import java.util.Map;

public record ExecutionRequest(
        String workflowCode,
        Map<String, Object> input
) {
}
