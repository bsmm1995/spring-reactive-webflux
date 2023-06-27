package com.bp.ensayo.repository;

import com.bp.ensayo.repository.entity.CustomerEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends R2dbcRepository<CustomerEntity, Long> {
}
