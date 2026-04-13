package com.example.transactionlogdemo.application.dto.response.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataSchema;

import java.util.List;

public record TransactionResponse(
        String id,
        String routeId,
        String code,
        String name,
        DataSchema data,
        Authentication authentication,
        List<String> dependsOn
) {
}
