package com.example.transactionlogdemo.domain.entity.transaction.retry;

public record Retry(
        Integer maxAttempts,
        Integer backoff
) {
}
