package com.example.transactionlogdemo.adapter.controller.route;

import com.example.transactionlogdemo.adapter.api.route.RouteApi;
import com.example.transactionlogdemo.application.dto.request.route.RouteRequest;
import com.example.transactionlogdemo.application.dto.response.route.RouteResponse;
import com.example.transactionlogdemo.application.mapper.request.route.RouteRequestMapper;
import com.example.transactionlogdemo.application.mapper.response.route.RouteResponseMapper;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteController implements RouteApi {
    RouteService routeService;

    RouteRequestMapper requestMapper;

    RouteResponseMapper responseMapper;

    @Override
    public ResponseEntity<RouteResponse> create(RouteRequest request) {
        Route route = routeService.createOrUpdate(requestMapper.toDomain(request));

        return ResponseEntity.ok(responseMapper.toDto(route));
    }

    @Override
    public ResponseEntity<RouteResponse> getByCode(String code) {
        RouteResponse response = routeService.getByCode(code)
                .map(responseMapper::toDto)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(response);
    }
}
