package com.bp.ensayo.repository;

import com.bp.ensayo.repository.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends R2dbcRepository<AccountEntity, Long> {
    Mono<AccountEntity> findByAccountNumber(String accountNumber);
}
