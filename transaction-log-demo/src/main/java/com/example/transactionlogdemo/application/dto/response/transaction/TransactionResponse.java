package com.example.transactionlogdemo.application.dto.response.transaction;

import com.example.transactionlogdemo.application.dto.response.transaction.authentication.AuthenticationResponse;
import com.example.transactionlogdemo.application.dto.response.transaction.retry.RetryResponse;
import com.example.transactionlogdemo.application.dto.response.transaction.schema.DataSchemaResponse;
import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.entity.transaction.schema.DataSchema;

import java.util.List;

public record TransactionResponse(
        String id,
        String routeCode,
        String code,
        String name,
        DataSchemaResponse data,
        AuthenticationResponse authentication,
        RetryResponse retry,
        List<String> dependsOn
) {
}
