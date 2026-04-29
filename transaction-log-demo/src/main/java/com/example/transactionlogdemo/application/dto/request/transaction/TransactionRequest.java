package com.example.transactionlogdemo.application.dto.request.transaction;

import java.util.List;
import java.util.Map;

public record TransactionRequest(
        String id,
        String routeCode,
        String code,
        String name,
        DataRequestSchemaRequest requestSchema,
        DataResponseSchemaRequest responseSchema,
        AuthenticationRequest authentication,
        RetryRequest retry,
        List<String> dependsOn
) {
    public record AuthenticationRequest(
            String type,
            String token
    ) { }

    public record RetryRequest(
            Integer maxAttempts,
            Integer backoff
    ) { }

    public record DataRequestSchemaRequest(
            Map<String, Object> params,
            Map<String, Object> body
    ) { }

    public record DataResponseSchemaRequest(
            Map<String, Object> body
    ) {
    }
}
