package com.example.transactionlogdemo.domain.service.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;

import java.util.Optional;

public interface WorkflowService {
    Workflow create(Workflow workflow);

    Optional<Workflow> getById(String workflowId);

    Workflow inactivate(String workflowId);

    Workflow activate(String workflowId);

    void remove(String workflowId);
}
