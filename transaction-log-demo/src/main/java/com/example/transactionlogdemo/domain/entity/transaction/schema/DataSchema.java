package com.example.transactionlogdemo.domain.entity.transaction.schema;

import com.example.transactionlogdemo.domain.entity.transaction.schema.ipo.IpoDataProcessing;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record DataSchema(
        Map<String, String> params,
        Map<String, String> body
) {
}
