package com.example.transactionlogdemo.infrastructure.persistence.adapter.mongo.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.repository.route.RouteRepository;
import com.example.transactionlogdemo.infrastructure.persistence.entity.route.RouteCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.transaction.TransactionCollection;
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
        Optional<RouteCollection> exist = getExist(route.id());
        if (exist.isPresent()) {
            upsertRoute = update(exist.get(), route);
        }

        if (exist.isEmpty()) {
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
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(criteria.buildQuery(), RouteCollection.class))
                .map(entityMapper::toDomain);
    }

    private Optional<RouteCollection> getExist(String id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        MongoCriteria mongoCriteria = MongoCriteria.builder()
                .field(MongoCriteria.Field.builder()
                        .key("id")
                        .value(id)
                        .build())
                .operator(MongoCriteria.EnumOperator.EQUAL)
                .build();

        return Optional.ofNullable(mongoTemplate.findOne(mongoCriteria.buildQuery(), RouteCollection.class));
    }

    private Route save(Route route) {
        RouteCollection prepareSaveRoute = entityMapper.toEntity(route);
        RouteCollection saveRoute = mongoTemplate.save(prepareSaveRoute);

        return entityMapper.toDomain(saveRoute);
    }

    private Route update(RouteCollection routeCollection, Route route) {
        entityMapper.update(routeCollection, route);
        RouteCollection saveRoute = mongoTemplate.save(routeCollection);

        return entityMapper.toDomain(saveRoute);
    }
}
