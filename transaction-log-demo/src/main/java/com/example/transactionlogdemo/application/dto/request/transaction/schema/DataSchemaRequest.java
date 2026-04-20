package com.example.transactionlogdemo.application.dto.request.transaction.schema;

import com.example.transactionlogdemo.application.dto.request.transaction.schema.ipo.IpoDataProcessingRequest;

import java.util.List;
import java.util.Map;

public record DataSchemaRequest(
        Map<String, Object> params,
        Map<String, Object> body
) {
}
