package com.example.transactionlogdemo.application.dto.response.transaction;

import com.example.transactionlogdemo.application.dto.response.transaction.authentication.AuthenticationResponse;
import com.example.transactionlogdemo.application.dto.response.transaction.retry.RetryResponse;
import com.example.transactionlogdemo.application.dto.response.transaction.schema.DataRequestSchemaResponse;
import com.example.transactionlogdemo.application.dto.response.transaction.schema.DataResponseSchemaResponse;

import java.util.List;

public record TransactionResponse(
        String id,
        String routeCode,
        String code,
        String name,
        DataRequestSchemaResponse requestSchema,
        DataResponseSchemaResponse responseSchema,
        AuthenticationResponse authentication,
        RetryResponse retry,
        List<String> dependsOn
) {
}
