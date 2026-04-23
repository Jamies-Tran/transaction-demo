package com.example.transactionlogdemo.domain.entity.log;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record TransactionLog(
        String id,
        String transactionId,
        Map<String, Object> requestBody,
        Map<String, Object> requestParam,
        String requestBy,
        String requestByName,
        LocalDateTime requestAt,
        LocalDateTime responseAt,
        Integer retryCount,
        String errMessage,
        String httpStatus,
        String status
) {
}
