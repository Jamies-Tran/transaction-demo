package com.example.transactionlogdemo.application.mapper.response.execution.result.workflow;

import com.example.transactionlogdemo.application.dto.response.execution.result.workflow.WorkflowExecutionResultResponse;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.DtoMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface WorkflowExecutionResultResponseMapper extends DtoMapper<WorkflowExecutionResultResponse,
        WorkflowExecutionResult> {
}
