package com.example.transactionlogdemo.application.dto.request.workflow;

import java.util.List;
import java.util.Map;

public record WorkflowRequest(
        String id,
        String name,
        String code,
        Map<String, Object> dataResultSchema,
        List<WorkflowTransactionRequest> transactions
) {
    public record WorkflowTransactionRequest(
            Integer executionOrder,
            String transactionCode
    ) {}
}
