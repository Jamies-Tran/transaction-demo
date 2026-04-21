package com.example.transactionlogdemo.domain.entity.transaction.retry;

import lombok.With;

public record Retry(
        @With
        Integer maxAttempts,
        Integer backoff
) {
}
