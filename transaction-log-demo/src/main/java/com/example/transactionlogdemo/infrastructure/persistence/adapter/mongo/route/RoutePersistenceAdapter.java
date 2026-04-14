package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.repository.route.RouteRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.route.RouteCollection;
import com.example.transactionlogdemo.infrastructure.persistence.mapper.route.RouteEntityMapper;
import com.example.transactionlogdemo.infrastructure.persistence.repository.mongo.criteria.MongoCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoutePersistenceAdapter implements RouteRepository {
    MongoTemplate mongoTemplate;

    RouteEntityMapper entityMapper;

    @Override
    public Route upsert(Route route) {
        Route upsertRoute = null;
        if (Objects.nonNull(route.id()) || Objects.nonNull(route.code())) {
            upsertRoute = update(route);
        }

        if (Objects.isNull(upsertRoute)) {
            upsertRoute = save(route);
        }

        return upsertRoute;
    }

    @Override
    public Optional<Route> findByCode(String code) {
        MongoCriteria criteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("code")
                        .value(code)
                        .build())
                .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(), RouteCollection.class))
                .map(entityMapper::toDomain);
    }

    private Route save(Route route) {
        RouteCollection prepareSaveRoute = entityMapper.toEntity(route);
        RouteCollection saveRoute = mongoTemplate.save(prepareSaveRoute);

        return entityMapper.toDomain(saveRoute);
    }

    private Route update(Route route) {
        MongoCriteria.MongoCriteriaBuilder criteriaBuilder = MongoCriteria.builder();
        if (Objects.nonNull(route.id())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("id")
                            .value(route.id())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL);
        }
        if (Objects.nonNull(route.code())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("code")
                            .value(route.code())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL);
        }
        if (Objects.nonNull(route.code()) && Objects.nonNull(route.id())) {
            criteriaBuilder
                    .field(MongoCriteria.Field.builder()
                            .key("code")
                            .value(route.code())
                            .build())
                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                    .and(List.of(MongoCriteria.builder()
                                    .field(MongoCriteria.Field.builder()
                                            .key("id")
                                            .value(route.id())
                                            .build())
                                    .compareOperator(MongoCriteria.EnumCompareOperator.EQUAL)
                            .build()));
        }
        MongoCriteria criteria = criteriaBuilder.build();
        Optional<RouteCollection> exist = Optional
                .ofNullable(mongoTemplate.findOne(criteria.buildQuery(), RouteCollection.class));
        if (exist.isEmpty()) {
            return null;
        }
        RouteCollection prepareSaveRoute = exist.get();
        entityMapper.update(prepareSaveRoute, route);
        RouteCollection saveRoute = mongoTemplate.save(prepareSaveRoute);

        return entityMapper.toDomain(saveRoute);
    }
}
