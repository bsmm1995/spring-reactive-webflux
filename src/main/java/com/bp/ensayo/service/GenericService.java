package com.bp.ensayo.service;

import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<D> {
    Mono<D> save(D data);

    Mono<D> update(Long id, D data);

    Mono<Void> delete(Long id);

    Flux<D> getAll(PageRequest pageRequest);

    Mono<D> getById(Long id);
}
