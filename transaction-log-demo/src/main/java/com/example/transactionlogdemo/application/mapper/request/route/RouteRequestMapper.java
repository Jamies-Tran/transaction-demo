package com.example.transactionlogdemo.application.mapper.request.route;

import com.example.transactionlogdemo.application.dto.request.route.RouteRequest;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface RouteRequestMapper extends DtoMapper<RouteRequest, Route> {
}
