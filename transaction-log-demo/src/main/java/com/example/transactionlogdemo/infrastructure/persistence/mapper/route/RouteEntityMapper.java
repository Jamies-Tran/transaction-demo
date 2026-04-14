package com.example.transactionlogdemo.infrastructure.persistence.mapper.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.route.RouteCollection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructGlobalConfig.class)
public interface RouteEntityMapper extends EntityMapper<RouteCollection, Route> {
    void update(@MappingTarget RouteCollection entity, Route domain);
}
