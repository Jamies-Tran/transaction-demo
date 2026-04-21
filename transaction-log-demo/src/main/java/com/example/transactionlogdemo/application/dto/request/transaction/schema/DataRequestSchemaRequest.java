package com.example.transactionlogdemo.application.dto.request.transaction.schema;

import java.util.Map;

public record DataRequestSchemaRequest(
        Map<String, Object> params,
        Map<String, Object> body
) {
}
