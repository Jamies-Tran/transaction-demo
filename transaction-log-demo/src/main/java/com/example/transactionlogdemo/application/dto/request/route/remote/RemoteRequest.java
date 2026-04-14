package com.example.transactionlogdemo.application.dto.request.route.remote;

public record RemoteRequest(
        String remoteCode,
        String serverId,
        String serverName,
        String host,
        String rewrite,
        String hostProxy,
        Boolean isProxy,
        Boolean isG
) {
}
