package com.example.transactionlogdemo.infrastructure.persistence.entity.log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document("transaction_log")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionLogCollection {
    String id;
    String transactionId;

    Map<String, Object> requestBody;
    Map<String, Object> requestParam;

    String requestBy;
    String requestByName;
    LocalDateTime requestAt;
    LocalDateTime responseAt;

    Integer retryCount;
    String errMessage;
    String httpStatus;
    String status;
}
