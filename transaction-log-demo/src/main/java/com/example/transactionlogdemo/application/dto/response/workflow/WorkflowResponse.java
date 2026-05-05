package com.example.transactionlogdemo.application.dto.response.workflow;

import java.util.List;
import java.util.Map;

public record WorkflowResponse(
        String id,
        String code,
        String name,
        Map<String, Object> dataResultSchema,
        List<WorkflowTransactionResponse> transactions,
        Boolean active
) {
    public record WorkflowTransactionResponse(
            Integer executionOrder,
            String transactionCode
    ) {}
}
