package com.bp.ensayo.ct1.repository;

import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
}
