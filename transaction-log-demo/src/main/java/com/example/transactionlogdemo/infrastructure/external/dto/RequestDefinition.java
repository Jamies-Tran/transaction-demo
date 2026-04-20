package com.example.transactionlogdemo.infrastructure.external.dto;

import com.example.transactionlogdemo.domain.enums.EnumMethod;

import java.util.Map;

public record RequestDefinition(
        EnumMethod method,
        String uri,
        Map<String, String> header,
        Map<String, Object> requestBody,
        Map<String, String> requestParam,
        Authentication authentication

) {
    public record Authentication(
            String type,
            String token
    ) {}
}
