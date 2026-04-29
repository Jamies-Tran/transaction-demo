package com.example.transactionlogdemo.infrastructure.external.dto;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import lombok.Builder;
import lombok.With;

import java.util.Map;

@Builder
public record RequestDefinition(
        @With
        EnumMethod method,
        @With
        String uri,
        String transactionId,
        Map<String, String> header,
        Map<String, Object> body,
        Map<String, Object> params,
        Transaction.Authentication authentication

) {
}
