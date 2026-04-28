package com.example.transactionlogdemo.adapter.controller.workflow;

import com.example.transactionlogdemo.adapter.api.workflow.WorkflowApi;
import com.example.transactionlogdemo.application.dto.request.workflow.WorkflowRequest;
import com.example.transactionlogdemo.application.dto.response.workflow.WorkflowResponse;
import com.example.transactionlogdemo.application.mapper.request.workflow.WorkflowRequestMapper;
import com.example.transactionlogdemo.application.mapper.response.workflow.WorkflowResponseMapper;
import com.example.transactionlogdemo.domain.entity.workflow.Workflow;
import com.example.transactionlogdemo.domain.service.workflow.WorkflowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkflowController implements WorkflowApi {
    WorkflowService workflowService;

    WorkflowRequestMapper requestMapper;

    WorkflowResponseMapper responseMapper;

    @Override
    public ResponseEntity<WorkflowResponse> create(WorkflowRequest request) {
        Workflow prepareSaveWorkflow = requestMapper.toDomain(request);
        Workflow saveWorkflow = workflowService.create(prepareSaveWorkflow);

        return ResponseEntity.ok(responseMapper.toDto(saveWorkflow));
    }

    @Override
    public ResponseEntity<WorkflowResponse> findByCode(String code) {
        WorkflowResponse response = workflowService.getByCode(code)
                .map(responseMapper::toDto)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> inactivateByCode(String code) {
        Workflow workflow = workflowService.inactivateByCode(code);

        return ResponseEntity.ok(responseMapper.toDto(workflow));
    }

    @Override
    public ResponseEntity<?> activateByCode(String code) {
        Workflow workflow = workflowService.activateByCode(code);

        return ResponseEntity.ok(responseMapper.toDto(workflow));
    }

    @Override
    public ResponseEntity<?> removeByCode(String code) {
        workflowService.removeByCode(code);

        return ResponseEntity.noContent().build();
    }
}
