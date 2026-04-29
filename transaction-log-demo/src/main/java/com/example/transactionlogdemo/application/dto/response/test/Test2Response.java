package com.example.transactionlogdemo.application.dto.response.test;

public record Test2Response(
        String name,
        Info info
) {
    public record Info(
            String phone,
            String email
    ) {}
}
