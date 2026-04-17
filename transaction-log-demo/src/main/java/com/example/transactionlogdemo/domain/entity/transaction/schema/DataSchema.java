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
    public Map<String, String> buildRequestMapping() {
        if (CollectionUtils.isEmpty(requestSchema)) {
            return null;
        }
        Map<String, Object> jsonSchema = requestSchema.stream()
                .collect(Collectors.toMap(IpoDataProcessing::label, IpoDataProcessing::field));

        return ObjectMapperUtils.buildMapping(jsonSchema);
    }

    public Map<String, String> buildResponseMapping() {
        if (CollectionUtils.isEmpty(responseSchema)) {
            return null;
        }
        Map<String, Object> jsonSchema = responseSchema.stream()
                .collect(Collectors.toMap(IpoDataProcessing::label, IpoDataProcessing::field));

        return ObjectMapperUtils.buildMapping(jsonSchema);
    }
}
