package com.example.transactionlogdemo.application.usecase.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.repository.route.RouteRepository;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteUseCase implements RouteService {
    RouteRepository repository;

    @Override
    public Route createOrUpdate(Route route) {
        return repository.upsert(route);
    }

    @Override
    public Optional<Route> getByCode(String code) {
        return repository.findByCode(code);
    }
}
