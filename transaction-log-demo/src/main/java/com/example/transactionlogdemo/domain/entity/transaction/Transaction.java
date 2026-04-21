package com.example.transactionlogdemo.domain.entity.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataRequestSchema;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataResponseSchema;

import java.util.List;

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
}
