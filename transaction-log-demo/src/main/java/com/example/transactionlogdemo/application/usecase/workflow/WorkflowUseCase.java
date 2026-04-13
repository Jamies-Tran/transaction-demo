package com.example.transactionlogdemo.application.usecase.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.repository.workflow.WorkflowRepository;
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
    WorkflowRepository repository;

    @Override
    public Workflow create(Workflow workflow) {
        return repository.upsert(workflow);
    }

    @Override
    public Optional<Workflow> getById(String workflowId) {
        return repository.findById(workflowId);
    }

    @Override
    public Workflow inactivate(String workflowId) {
        Workflow workflow = repository.findById(workflowId)
                .orElseThrow(RuntimeException::new);

        return repository.upsert(workflow.inactivate());
    }

    @Override
    public Workflow activate(String workflowId) {
        Workflow workflow = repository.findById(workflowId)
                .orElseThrow(RuntimeException::new);

        return repository.upsert(workflow.activate());
    }

    @Override
    public void remove(String workflowId) {
        repository.delete(workflowId);
    }
}
