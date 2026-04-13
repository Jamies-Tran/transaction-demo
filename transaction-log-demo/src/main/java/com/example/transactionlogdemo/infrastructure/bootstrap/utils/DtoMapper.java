package com.example.transactionlogdemo.infrastructure.bootstrap.utils;

import java.util.List;

public interface DtoMapper<Dto, Domain> {
    Dto toDto(Domain domain);

    Domain toDomain(Dto entity);

    List<Dto> toEntity(List<Domain> domain);

    List<Domain> toDomain(List<Dto> dto);
}
