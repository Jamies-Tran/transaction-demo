package com.example.transactionlogdemo.application.dto.response.workflow;

import java.util.List;

public record WorkflowResponse(
        String id,
        String name,
        List<String> transactionCodes,
        Boolean active
) {
}
