package com.example.transactionlogdemo.domain.repository.route;

import com.example.transactionlogdemo.domain.entity.route.Route;

import java.util.Optional;

public interface RouteRepository {
    Route upsert(Route route);

    Optional<Route> findByCode(String code);
}
