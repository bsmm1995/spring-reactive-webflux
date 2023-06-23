package com.bp.ensayo.repository;

import com.bp.ensayo.domain.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.Optional;

public interface AccountRepository extends R2dbcRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
