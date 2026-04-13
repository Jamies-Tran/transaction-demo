package com.example.transactionlogdemo.application.dto.request.transaction.retry;

public record RetryRequest(
        Integer maxAttempts,
        Integer backoff
) {
}
