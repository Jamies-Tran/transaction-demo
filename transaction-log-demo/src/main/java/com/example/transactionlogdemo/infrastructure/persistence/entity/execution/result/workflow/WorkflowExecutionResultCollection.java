package com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.workflow;

import com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.transaction.ExecutionResultCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("workflow_execution_result")
public class WorkflowExecutionResultCollection {
    String id;
    String workflowId;
    LocalDateTime executeAt;
    List<ExecutionResultCollection> executionResults;
}
