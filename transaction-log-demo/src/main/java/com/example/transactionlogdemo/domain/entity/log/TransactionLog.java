package com.example.transactionlogdemo.domain.entity.log;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record TransactionLog(
        String id,
        String transactionId,
        String requestBody,
        String requestParam,
        String requestBy,
        String requestByName,
        Long requestAtMillis,
        Long responseAtMillis,
        Integer retryCount,
        String errMessage,
        String httpStatus,
        String status
) {
}
