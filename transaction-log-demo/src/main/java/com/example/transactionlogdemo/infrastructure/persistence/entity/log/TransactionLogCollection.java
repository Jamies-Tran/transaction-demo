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

    String requestBody;
    String requestParam;

    String requestBy;
    String requestByName;
    Long requestAtMillis;
    Long responseAtMillis;

    Integer retryCount;
    String errMessage;
    String httpStatus;
    String status;
}
