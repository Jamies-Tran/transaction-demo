package com.example.transactionlogdemo.application.dto.response.transaction.schema;

import com.example.transactionlogdemo.application.dto.response.transaction.schema.ipo.IpoDataProcessingResponse;

import java.util.List;
import java.util.Map;

public record DataSchemaResponse(
        Map<String, Object> params,
        Map<String, Object> body
) {
}
