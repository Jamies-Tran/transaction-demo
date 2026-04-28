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
    public Optional<Workflow> getByCode(String workflowCode) {
        return repository.findByCode(workflowCode);
    }

    @Override
    public Workflow inactivateByCode(String code) {
        Workflow workflow = repository.findByCode(code)
                .orElseThrow(RuntimeException::new);

        return repository.upsert(workflow.inactivate());
    }

    @Override
    public Workflow activateByCode(String code) {
        Workflow workflow = repository.findByCode(code)
                .orElseThrow(RuntimeException::new);

        return repository.upsert(workflow.activate());
    }

    @Override
    public void removeByCode(String code) {
        repository.deleteByCode(code);
    }
}
