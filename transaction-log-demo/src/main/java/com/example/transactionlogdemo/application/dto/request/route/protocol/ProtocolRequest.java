package com.example.transactionlogdemo.application.dto.request.route.protocol;

public record ProtocolRequest(
        String connector,
        String method
) {
}
