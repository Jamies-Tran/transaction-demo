package com.example.transactionlogdemo.domain.entity.route;

public record Route(
        String id,
        String code,
        String name,
        Protocol protocol,
        Remote remote,
        Path path
) {
    public record Path(
            String uri,
            String inUri
    ) { }

    public record Protocol(
            String connector,
            String method
    ) { }

    public record Remote(
            String remoteCode,
            String serverId,
            String serverName,
            String host,
            String rewrite,
            String hostProxy,
            Boolean isProxy,
            Boolean isG
    ) { }
}
