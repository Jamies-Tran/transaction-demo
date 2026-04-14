package com.example.transactionlogdemo.application.mapper.response.route;

import com.example.transactionlogdemo.application.dto.response.route.RouteResponse;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface RouteResponseMapper extends DtoMapper<RouteResponse, Route> {
}
