package com.example.transactionlogdemo.domain.entity.transaction;

import lombok.With;

import java.util.List;
import java.util.Map;

public record Transaction(
        String id,
        String routeCode,
        String code,
        String name,
        DataRequestSchema requestSchema,
        DataResponseSchema responseSchema,
        Authentication authentication,
        Retry retry,
        List<String> dependsOn
) {
    public record Authentication(
            String type,
            String token
    ) {}

    public record Retry(
            @With
            Integer maxAttempts,
            Integer backoff
    ) {}

    public record DataRequestSchema(
            Map<String, Object> params,
            Map<String, Object> body
    ) { }

    public record DataResponseSchema(
            Map<String, Object> body
    ) {
    }
}
