package com.example.transactionlogdemo.domain.entity.transaction.schema;

import java.util.Map;

public record DataRequestSchema(
        Map<String, Object> params,
        Map<String, Object> body
) {
}
