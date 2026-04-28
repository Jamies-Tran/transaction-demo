package com.example.transactionlogdemo.infrastructure.persistence.mapper.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.WorkflowCollection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructGlobalConfig.class)
public interface WorkflowEntityMapper extends EntityMapper<WorkflowCollection, Workflow> {
    void update(@MappingTarget WorkflowCollection entity, Workflow dto);
}
