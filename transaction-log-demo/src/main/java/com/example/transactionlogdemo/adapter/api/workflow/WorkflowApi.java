package com.example.transactionlogdemo.adapter.api.workflow;

import com.example.transactionlogdemo.application.dto.request.workflow.WorkflowRequest;
import com.example.transactionlogdemo.application.dto.response.workflow.WorkflowResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/demo/workflow")
public interface WorkflowApi {
    @PostMapping
    ResponseEntity<WorkflowResponse> create(@RequestBody WorkflowRequest request);

    @GetMapping("/{code}")
    ResponseEntity<WorkflowResponse> findByCode(@PathVariable String code);

    @PatchMapping("/{code}/inactivate")
    ResponseEntity<?> inactivateByCode(@PathVariable String code);

    @PatchMapping("/{code}/activate")
    ResponseEntity<?> activateByCode(@PathVariable String code);

    @DeleteMapping("/{code}")
    ResponseEntity<?> removeByCode(@PathVariable String code);
}
