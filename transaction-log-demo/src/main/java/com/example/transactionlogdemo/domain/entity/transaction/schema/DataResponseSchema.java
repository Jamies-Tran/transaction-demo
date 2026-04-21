package com.example.transactionlogdemo.domain.entity.transaction.schema;

import java.util.Map;

public record DataResponseSchema(
        Map<String, Object> body
) {
}
