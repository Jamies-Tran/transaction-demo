package com.example.transactionlogdemo.infrastructure.bootstrap.utils.mapstruct;

import java.util.List;

public interface EntityMapper<Entity, Domain> {
    Entity toEntity(Domain domain);

    Domain toDomain(Entity entity);

    List<Entity> toEntity(List<Domain> domain);

    List<Domain> toDomain(List<Entity> entity);
}
