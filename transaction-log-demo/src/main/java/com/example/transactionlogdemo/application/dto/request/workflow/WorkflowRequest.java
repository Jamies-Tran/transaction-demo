package com.example.transactionlogdemo.application.dto.request.workflow;

import java.util.List;

public record WorkflowRequest(
        String id,
        String name,
        String code,
        List<String> transactionCodes
) {
}
