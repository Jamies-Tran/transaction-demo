package com.example.transactionlogdemo.application.dto.request.workflow;

import java.util.List;

public record WorkflowRequest(
        String name,
        List<String> transactionCodes
) {
}
