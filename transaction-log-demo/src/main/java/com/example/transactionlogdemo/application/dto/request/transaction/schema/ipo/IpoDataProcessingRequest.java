package com.example.transactionlogdemo.application.dto.request.transaction.schema.ipo;

public record IpoDataProcessingRequest(
        String scope,
        String field,
        String label
) {
}
