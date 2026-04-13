package com.example.transactionlogdemo.domain.entity.transaction.schema;

import com.example.transactionlogdemo.domain.entity.transaction.schema.ipo.IpoDataProcessing;

import java.util.List;

public record DataSchema(
        List<IpoDataProcessing> requestSchema,
        List<IpoDataProcessing> responseSchema
) {
}
