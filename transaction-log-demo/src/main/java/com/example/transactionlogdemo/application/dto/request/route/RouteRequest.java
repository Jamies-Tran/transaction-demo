package com.example.transactionlogdemo.application.dto.request.route;

import com.example.transactionlogdemo.application.dto.request.route.path.PathRequest;
import com.example.transactionlogdemo.application.dto.request.route.protocol.ProtocolRequest;
import com.example.transactionlogdemo.application.dto.request.route.remote.RemoteRequest;

public record RouteRequest(
        String code,
        String name,
        ProtocolRequest protocol,
        RemoteRequest remote,
        PathRequest path
) {
}
