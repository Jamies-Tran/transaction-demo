package com.example.transactionlogdemo.application.dto.request.transaction.schema;

import com.example.transactionlogdemo.application.dto.request.transaction.schema.ipo.IpoDataProcessingRequest;

import java.util.List;

public record DataSchemaRequest(
        List<IpoDataProcessingRequest> requestSchema,
        List<IpoDataProcessingRequest> responseSchema
) {
}
