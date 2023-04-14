package com.bp.ensayo.ct1.repository;

import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
