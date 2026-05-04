package com.example.transactionlogdemo.application.dto.request.test;

public record TestRequest(
        String name,
        InfoRequest info
) {
    public record InfoRequest(
            String phone,
            String email
    ) {}
}
