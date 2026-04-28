package com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Builder
public record MongoCriteria(
        Field field,
        EnumOperator operator,
        List<MongoCriteria> and,
        List<MongoCriteria> or
) {
    // MongoCriteria.builder().field().operator().and(...).or(...)

    @Builder
    public record Field<T>(
            String key,
            T value,
            List<T> values
    ) {}

    public enum EnumOperator {
        EQUAL,
        NOT_EQUAL,
        LESS,
        GREATER,
        LESS_EQUAL,
        GREATER_EQUAL,
        IN,
        NOT_IN,
        BETWEEN
    }

    public Query buildQuery() {
        Criteria criteria = new Criteria();
        if (Objects.nonNull(field) && Objects.nonNull(operator)) {
            criteria = Criteria.where(field.key);
            switch (operator) {
                case EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.is(field.value);
                }
                case NOT_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.ne(field.value);
                }
                case LESS -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lt(field.value);
                }
                case LESS_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lte(field.value);
                }
                case GREATER -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.gt(field.value);
                }
                case GREATER_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.gte(field.value);
                }
                case IN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.in(field.values);
                }
                case NOT_IN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.nin(field.values);
                }
                case BETWEEN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lte(field.values.getFirst()).gt(field.values.get(1));
                }
            }
        }

        if (!CollectionUtils.isEmpty(and)) {
            for (MongoCriteria mongoCriteria : and) {
                Field f = mongoCriteria.field;
                Criteria c = Criteria.where(f.key);
                switch (mongoCriteria.operator) {
                    case EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.is(f.value));
                    }
                    case NOT_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.ne(f.value));
                    }
                    case LESS -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lt(f.value));
                    }
                    case LESS_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lte(f.value));
                    }
                    case GREATER -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gt(f.value));
                    }
                    case GREATER_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gte(f.value));
                    }
                    case IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.in(f.value));
                    }
                    case NOT_IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.nin(f.value));
                    }
                    case BETWEEN -> {
                        if (Objects.isNull(f.values)) {
                            log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gte(f.values.getFirst()).lt(f.values.getLast()));
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(or)) {
            for (MongoCriteria mongoCriteria : or) {
                Field f = mongoCriteria.field;
                Criteria c = Criteria.where(f.key);
                switch (mongoCriteria.operator) {
                    case EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.is(f.value));
                    }
                    case NOT_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.ne(f.value));
                    }
                    case LESS -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.lt(f.value));
                    }
                    case LESS_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.lte(f.value));
                    }
                    case GREATER -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.gt(f.value));
                    }
                    case GREATER_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.gte(f.value));
                    }
                    case IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.in(f.value));
                    }
                    case NOT_IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.nin(f.value));
                    }
                    case BETWEEN -> {
                        if (Objects.isNull(f.values)) {
                            log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lte(f.values.getFirst()).gt(f.values.get(1)));
                    }
                }
            }
        }

        return Query.query(criteria);
    }

    public Query buildQuery(PageRequest pageRequest) {
        Criteria criteria = new Criteria();
        if (Objects.nonNull(field) && Objects.nonNull(operator)) {
            criteria = Criteria.where(field.key);
            switch (operator) {
                case EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.is(field.value);
                }
                case NOT_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.ne(field.value);
                }
                case LESS -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lt(field.value);
                }
                case LESS_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lte(field.value);
                }
                case GREATER -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.gt(field.value);
                }
                case GREATER_EQUAL -> {
                    if (Objects.isNull(field.value)) {
                        log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.gte(field.value);
                }
                case IN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.in(field.values);
                }
                case NOT_IN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.nin(field.values);
                }
                case BETWEEN -> {
                    if (Objects.isNull(field.values)) {
                        log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                        throw new RuntimeException();
                    }
                    criteria.lte(field.values.getFirst()).gt(field.values.get(1));
                }
            }
        }

        if (!CollectionUtils.isEmpty(and)) {
            for (MongoCriteria mongoCriteria : and) {
                Field f = mongoCriteria.field;
                Criteria c = Criteria.where(f.key);
                switch (mongoCriteria.operator) {
                    case EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.is(f.value));
                    }
                    case NOT_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.ne(f.value));
                    }
                    case LESS -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lt(f.value));
                    }
                    case LESS_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lte(f.value));
                    }
                    case GREATER -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gt(f.value));
                    }
                    case GREATER_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gte(f.value));
                    }
                    case IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.in(f.value));
                    }
                    case NOT_IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.nin(f.value));
                    }
                    case BETWEEN -> {
                        if (Objects.isNull(f.values)) {
                            log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.gte(f.values.getFirst()).lt(f.values.getLast()));
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(or)) {
            for (MongoCriteria mongoCriteria : or) {
                Field f = mongoCriteria.field;
                Criteria c = Criteria.where(f.key);
                switch (mongoCriteria.operator) {
                    case EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.is(f.value));
                    }
                    case NOT_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.ne(f.value));
                    }
                    case LESS -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.lt(f.value));
                    }
                    case LESS_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.lte(f.value));
                    }
                    case GREATER -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.gt(f.value));
                    }
                    case GREATER_EQUAL -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.gte(f.value));
                    }
                    case IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.in(f.value));
                    }
                    case NOT_IN -> {
                        if (Objects.isNull(f.value)) {
                            log.error("[MongoCriteria.buildQuery()] message: value không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.orOperator(c.nin(f.value));
                    }
                    case BETWEEN -> {
                        if (Objects.isNull(f.values)) {
                            log.error("[MongoCriteria.buildQuery()] message: values không được null hoặc bỏ trống");
                            throw new RuntimeException();
                        }
                        criteria.andOperator(c.lte(f.values.getFirst()).gt(f.values.get(1)));
                    }
                }
            }
        }

        return Query.query(criteria).with(pageRequest);
    }
}
