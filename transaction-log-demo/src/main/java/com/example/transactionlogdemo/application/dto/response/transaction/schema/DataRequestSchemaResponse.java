package com.example.transactionlogdemo.application.dto.response.transaction.schema;

import java.util.Map;

public record DataRequestSchemaResponse(
        Map<String, Object> params,
        Map<String, Object> body
) {
}
