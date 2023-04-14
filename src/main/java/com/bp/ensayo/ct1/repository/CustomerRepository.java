package com.bp.ensayo.ct1.repository;

import com.bp.ensayo.ct1.domain.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
