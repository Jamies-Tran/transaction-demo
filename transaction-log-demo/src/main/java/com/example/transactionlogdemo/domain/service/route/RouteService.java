package com.example.transactionlogdemo.domain.service.route;

import com.example.transactionlogdemo.domain.entity.route.Route;

import java.util.Optional;

public interface RouteService {
    Route createOrUpdate(Route route);

    Optional<Route> getByCode(String code);
}
