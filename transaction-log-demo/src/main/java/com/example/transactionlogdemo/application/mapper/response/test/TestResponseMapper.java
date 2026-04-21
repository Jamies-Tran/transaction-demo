package com.example.transactionlogdemo.application.mapper.response.test;

import com.example.transactionlogdemo.application.dto.response.test.TestResponse;
import com.example.transactionlogdemo.domain.entity.test.Test;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface TestResponseMapper extends DtoMapper<TestResponse, Test> {
}
