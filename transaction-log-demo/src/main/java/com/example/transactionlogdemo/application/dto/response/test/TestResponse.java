package com.example.transactionlogdemo.application.dto.response.test;

public record TestResponse(
        String name,
        InfoResponse info
) {
    public record InfoResponse(
            String phone,
            String email
    ) {}
}
