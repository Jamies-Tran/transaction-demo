package com.example.transactionlogdemo.adapter.api.route;

import com.example.transactionlogdemo.application.dto.request.route.RouteRequest;
import com.example.transactionlogdemo.application.dto.response.route.RouteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/demo/route")
public interface RouteApi {
    @PostMapping
    ResponseEntity<RouteResponse> create(@RequestBody RouteRequest request);

    @GetMapping("/{code}")
    ResponseEntity<RouteResponse> getByCode(@PathVariable String code);
}
