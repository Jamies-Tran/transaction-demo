package com.example.transactionlogdemo.application.dto.response.test;

import lombok.Builder;

@Builder
public record Test2Response(
        String name,
        String phone,
        String email
) {
}
