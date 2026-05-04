package com.example.transactionlogdemo.application.dto.response.transaction;

import java.util.List;
import java.util.Map;

public record TransactionResponse(
        String id,
        String routeCode,
        String code,
        String name,
        DataRequestSchemaResponse requestSchema,
        DataResponseSchemaResponse responseSchema,
        AuthenticationResponse authentication,
        RetryResponse retry
) {
    public record AuthenticationResponse(
            String type,
            String token
    ) { }

    public record RetryResponse(
            Integer maxAttempts,
            Integer backoff
    ) { }

    public record DataRequestSchemaResponse(
            Map<String, Object> params,
            Map<String, Object> body,
            List<Object> pathVariable
    ) { }

    public record DataResponseSchemaResponse(
            Map<String, Object> body
    ) { }
}
