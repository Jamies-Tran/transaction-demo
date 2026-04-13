package com.example.transactionlogdemo.application.usecase.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.service.workflow.WorkflowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowUseCase implements WorkflowService {
    @Override
    public Workflow create(Workflow workflow) {
        return null;
    }

    @Override
    public Optional<Workflow> getById(String workflowId) {
        return Optional.empty();
    }

    @Override
    public Workflow inactive(String workflowId) {
        return null;
    }

    @Override
    public Workflow active(String workflowId) {
        return null;
    }

    @Override
    public void remove(String workflowId) {

    }
}
