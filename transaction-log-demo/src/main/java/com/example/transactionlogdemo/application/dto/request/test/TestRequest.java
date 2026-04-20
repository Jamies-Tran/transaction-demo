package com.example.transactionlogdemo.application.dto.request.test;

public record TestRequest(
        String name,
        String phone,
        String email
) {
    public record Info(
            String phone,
            String email
    ) {}
}
