package com.example.transactionlogdemo.application.dto.request.transaction.authentication;

public record AuthenticationRequest(
        String type,
        String token
) {
}
