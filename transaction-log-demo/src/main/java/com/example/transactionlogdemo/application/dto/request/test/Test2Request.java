package com.example.transactionlogdemo.application.dto.request.test;

import jakarta.validation.constraints.NotNull;

public record Test2Request(
        @NotNull
        String name,
        @NotNull
        String phone,
        @NotNull
        String email
) {
}
