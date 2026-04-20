package com.example.transactionlogdemo.domain.entity.route;

import com.example.transactionlogdemo.domain.entity.route.path.Path;
import com.example.transactionlogdemo.domain.entity.route.protocol.Protocol;
import com.example.transactionlogdemo.domain.entity.route.remote.Remote;

public record Route(
        String id,
        String code,
        String name,
        Protocol protocol,
        Remote remote,
        Path path
) {
}
