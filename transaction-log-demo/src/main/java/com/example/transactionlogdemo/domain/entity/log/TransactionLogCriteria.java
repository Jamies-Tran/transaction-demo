package com.example.transactionlogdemo.domain.entity.log;

import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record TransactionLogCriteria(
        List<LocalDateTime> timeRange,
        List<String> status,
        List<String> httpStatus,
        String transactionId
) {
    public Query buildQuery(PageRequest pageRequest) {
        List<MongoCriteria> andCriteria = new ArrayList<>();
        if (!CollectionUtils.isEmpty(timeRange)) {
            MongoCriteria criteria = MongoCriteria.builder()
                    .field(MongoCriteria.Field.<LocalDateTime>builder()
                            .key("occurrenceAt")
                            .values(timeRange)
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.BETWEEN)
                    .build();
            andCriteria.add(criteria);
        }
        if (!CollectionUtils.isEmpty(status)) {
            MongoCriteria criteria = MongoCriteria.builder()
                    .field(MongoCriteria.Field.<String>builder()
                            .key("status")
                            .values(status)
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.IN)
                    .build();
            andCriteria.add(criteria);
        }
        if (!CollectionUtils.isEmpty(httpStatus)) {
            MongoCriteria criteria = MongoCriteria.builder()
                    .field(MongoCriteria.Field.<String>builder()
                            .key("httpStatus")
                            .values(httpStatus)
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.IN)
                    .build();
            andCriteria.add(criteria);
        }
        if (StringUtils.hasText(transactionId)) {
            MongoCriteria criteria = MongoCriteria.builder()
                    .field(MongoCriteria.Field.<String>builder()
                            .key("transactionId")
                            .value(transactionId)
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                    .build();
            andCriteria.add(criteria);
        }
        return MongoCriteria.builder()
                .and(andCriteria)
                .build()
                .buildQuery(pageRequest);
    }
}
