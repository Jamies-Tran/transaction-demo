package com.example.transactionlogdemo.application.dto.response.test;

public record TestResponse(
        String name,
        Info info
) {
    public record Info(
            String phone,
            String email
    ) {}
}
