package com.example.transactionlogdemo.domain.service.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;

import java.util.Optional;

public interface WorkflowService {
    Workflow create(Workflow workflow);

    Optional<Workflow> getByCode(String workflowCode);

    Workflow inactivateByCode(String code);

    Workflow activateByCode(String code);

    void removeByCode(String code);
}
