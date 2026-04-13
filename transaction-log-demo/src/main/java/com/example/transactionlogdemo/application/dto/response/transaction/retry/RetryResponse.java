package com.example.transactionlogdemo.application.dto.response.transaction.retry;

public record RetryResponse(
        Integer maxAttempts,
        Integer backoff
) {
}
