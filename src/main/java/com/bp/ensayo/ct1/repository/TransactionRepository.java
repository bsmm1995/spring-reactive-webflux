package com.bp.ensayo.ct1.repository;

import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import com.bp.ensayo.ct1.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByAccount(AccountEntity account);
}
