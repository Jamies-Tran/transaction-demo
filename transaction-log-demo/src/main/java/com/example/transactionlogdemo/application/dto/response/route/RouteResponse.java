package com.example.transactionlogdemo.application.dto.response.route;

public record RouteResponse(
        String id,
        String code,
        String name,
        ProtocolResponse protocol,
        RemoteResponse remote,
        PathResponse path
) {
    public record PathResponse(
            String uri,
            String inUri
    ) { }

    public record ProtocolResponse(
            String connector,
            String method
    ) { }

    public record RemoteResponse(
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
