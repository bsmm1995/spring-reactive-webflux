package com.bp.ensayo.repository;

import com.bp.ensayo.domain.entity.TransactionEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends R2dbcRepository<TransactionEntity, Long> {
    Flux<TransactionEntity> findAllByAccountId(Long accountId);
}
