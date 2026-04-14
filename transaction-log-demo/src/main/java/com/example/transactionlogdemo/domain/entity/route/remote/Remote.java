package com.example.transactionlogdemo.domain.entity.route.remote;

public record Remote(
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
