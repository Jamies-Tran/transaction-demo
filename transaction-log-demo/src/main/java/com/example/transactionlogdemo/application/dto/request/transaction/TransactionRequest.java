package com.example.transactionlogdemo.application.dto.request.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataSchema;

import java.util.List;

public record TransactionRequest(
        String routeId,
        String code,
        String name,
        DataSchema data,
        Authentication authentication,
        List<String> dependsOn
) {
}
