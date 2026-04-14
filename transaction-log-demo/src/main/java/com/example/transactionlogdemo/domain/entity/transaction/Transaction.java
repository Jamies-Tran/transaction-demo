package com.example.transactionlogdemo.domain.entity.transaction;

import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataSchema;

import java.util.List;

public record Transaction(
        String id,
        String routeId,

        String code,
        String name,
        DataSchema data,
        Authentication authentication,
        Retry retry,
        List<String> dependsOn
) {
}
