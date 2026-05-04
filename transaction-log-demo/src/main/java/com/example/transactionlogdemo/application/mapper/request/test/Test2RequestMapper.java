package com.example.transactionlogdemo.application.mapper.request.test;

import com.example.transactionlogdemo.application.dto.request.test.Test2Request;
import com.example.transactionlogdemo.application.dto.request.test.TestRequest;
import com.example.transactionlogdemo.domain.entity.test.Test;
import com.example.transactionlogdemo.domain.entity.test.Test2;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface Test2RequestMapper extends DtoMapper<Test2Request, Test2> {
}
