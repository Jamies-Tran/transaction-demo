package com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.retry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetryDocument {
    Integer maxAttempts;
    Integer backoff;
}
