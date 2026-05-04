package com.example.transactionlogdemo.domain.entity.test;

public record Test(
        String name,
        Info info
) {
    public record Info(
            String phone,
            String email
    ) {}
}
