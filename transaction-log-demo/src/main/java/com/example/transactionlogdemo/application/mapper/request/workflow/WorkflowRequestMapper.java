package com.example.transactionlogdemo.application.mapper.request.workflow;

import com.example.transactionlogdemo.application.dto.request.workflow.WorkflowRequest;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface WorkflowRequestMapper extends DtoMapper<WorkflowRequest, Workflow> {
}
