package com.example.transactionlogdemo.application.dto.request.transaction;

import com.example.transactionlogdemo.application.dto.request.transaction.authentication.AuthenticationRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.retry.RetryRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.schema.DataRequestSchemaRequest;
import com.example.transactionlogdemo.application.dto.request.transaction.schema.DataResponseSchemaRequest;

import java.util.List;

public record TransactionRequest(
        String routeCode,
        String code,
        String name,
        DataRequestSchemaRequest requestSchema,
        DataResponseSchemaRequest responseSchema,
        AuthenticationRequest authentication,
        RetryRequest retry,
        List<String> dependsOn
) {
}
