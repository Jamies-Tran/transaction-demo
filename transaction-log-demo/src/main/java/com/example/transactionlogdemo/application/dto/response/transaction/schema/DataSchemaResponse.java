package com.example.transactionlogdemo.application.dto.response.transaction.schema;

import com.example.transactionlogdemo.application.dto.response.transaction.schema.ipo.IpoDataProcessingResponse;

import java.util.List;

public record DataSchemaResponse(
        List<IpoDataProcessingResponse> requestSchema,
        List<IpoDataProcessingResponse> responseSchema
) {
}
