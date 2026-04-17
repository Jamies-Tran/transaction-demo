package com.example.transactionlogdemo.application.mapper.response.workflow;

import com.example.transactionlogdemo.application.dto.response.workflow.WorkflowResponse;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface WorkflowResponseMapper extends DtoMapper<WorkflowResponse, Workflow> {
}
