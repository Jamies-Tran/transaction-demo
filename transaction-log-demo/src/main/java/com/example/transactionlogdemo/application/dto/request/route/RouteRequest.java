package com.example.transactionlogdemo.application.dto.request.route;

import com.example.transactionlogdemo.application.dto.request.route.path.PathRequest;
import com.example.transactionlogdemo.application.dto.request.route.remote.RemoteRequest;

public record RouteRequest(
        String id,
        String code,
        String name,
        RemoteRequest remote,
        PathRequest path
) {
}
