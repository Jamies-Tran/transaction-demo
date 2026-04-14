package com.example.transactionlogdemo.application.dto.response.route.remote;

public record RemoteResponse(
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
