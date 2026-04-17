package com.example.transactionlogdemo.domain.entity.transaction.schema;

import com.example.transactionlogdemo.domain.entity.transaction.schema.ipo.IpoDataProcessing;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record DataSchema(
        List<IpoDataProcessing> requestSchema,
        List<IpoDataProcessing> responseSchema
) {
    public String jsonStringRequestSchema() {
        if (CollectionUtils.isEmpty(requestSchema)) {
            return null;
        }
        Map<String, Object> jsonSchema = requestSchema.stream()
                .collect(Collectors.toMap(IpoDataProcessing::label, IpoDataProcessing::field));

        return ObjectMapperUtils.convertToString(jsonSchema);
    }

    public String jsonStringResponseSchema() {
        if (CollectionUtils.isEmpty(responseSchema)) {
            return null;
        }
        Map<String, Object> jsonSchema = responseSchema.stream()
                .collect(Collectors.toMap(IpoDataProcessing::label, IpoDataProcessing::field));

        return ObjectMapperUtils.convertToString(jsonSchema);
    }
}
