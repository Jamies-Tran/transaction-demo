package com.example.transactionlogdemo.application.dto.request.route;

public record RouteRequest(
        String id,
        String code,
        String name,
        ProtocolRequest protocol,
        RemoteRequest remote,
        PathRequest path
) {
    public record PathRequest(
            String uri,
            String inUri
    ) { }

    public record ProtocolRequest(
            String connector,
            String method
    ) { }

    public record RemoteRequest(
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
