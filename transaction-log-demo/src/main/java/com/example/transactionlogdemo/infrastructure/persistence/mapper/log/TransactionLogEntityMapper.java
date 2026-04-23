package com.example.transactionlogdemo.infrastructure.persistence.mapper.log;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.log.TransactionLogCollection;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface TransactionLogEntityMapper extends EntityMapper<TransactionLogCollection, TransactionLog> {
}
