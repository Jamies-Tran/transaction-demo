package com.example.transactionlogdemo.application.dto.request.test;

import jakarta.validation.constraints.NotNull;

public record Test2Request(
        @NotNull
        String name,
        @NotNull
        Info info
) {
    public record Info(
            String phone,
            String email
    ) {}
}
