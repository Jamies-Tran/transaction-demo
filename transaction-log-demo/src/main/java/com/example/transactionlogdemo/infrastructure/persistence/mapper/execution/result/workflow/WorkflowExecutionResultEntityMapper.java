package com.example.transactionlogdemo.infrastructure.persistence.mapper.execution.result.workflow;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.infrastructure.bootstrap.configuration.MapStructGlobalConfig;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct.EntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.workflow.WorkflowExecutionResultCollection;
import org.mapstruct.Mapper;

@Mapper(config = MapStructGlobalConfig.class)
public interface WorkflowExecutionResultEntityMapper extends EntityMapper<WorkflowExecutionResultCollection,
        WorkflowExecutionResult> {
}
