package com.example.transactionlogdemo.application.dto.response.route;

import com.example.transactionlogdemo.application.dto.response.route.path.PathResponse;
import com.example.transactionlogdemo.application.dto.response.route.protocol.ProtocolResponse;
import com.example.transactionlogdemo.application.dto.response.route.remote.RemoteResponse;

public record RouteResponse(
        String id,
        String code,
        String name,
        ProtocolResponse protocol,
        RemoteResponse remote,
        PathResponse path
) {
}
