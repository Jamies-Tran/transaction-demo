package com.example.transactionlogdemo.application.dto.request.transaction;

import com.example.transactionlogdemo.application.dto.request.transaction.authentication.AuthenticationRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.retry.RetryRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.schema.DataSchemaRequest;
import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataSchema;

import java.util.List;

public record TransactionRequest(
        String routeId,
        String code,
        String name,
        DataSchemaRequest data,
        AuthenticationRequest authentication,
        RetryRequest retry,
        List<String> dependsOn
) {
}
