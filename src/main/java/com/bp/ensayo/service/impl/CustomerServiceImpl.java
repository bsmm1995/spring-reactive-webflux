package com.bp.ensayo.service.impl;

import com.bp.ensayo.repository.entity.CustomerEntity;
import com.bp.ensayo.repository.CustomerRepository;
import com.bp.ensayo.service.CustomerService;
import com.bp.ensayo.service.dto.CustomerDTO;
import com.bp.ensayo.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerDTO> save(CustomerDTO data) {
        CustomerEntity entity = CustomerMapper.INSTANCE.toEntity(data);
        return customerRepository.save(entity).map(CustomerMapper.INSTANCE::toDto);
    }

    @Override
    public Mono<CustomerDTO> update(Long id, CustomerDTO data) {
        return customerRepository.findById(id)
                .flatMap(entity -> {
                    entity.setName(data.getName());
                    entity.setLastname(data.getLastname());
                    entity.setIdentificationType(data.getIdentificationType());
                    return customerRepository.save(entity);
                })
                .map(CustomerMapper.INSTANCE::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return customerRepository.deleteById(id);
    }

    @Override
    public Flux<CustomerDTO> getAll(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest.getSort())
                .map(CustomerMapper.INSTANCE::toDto)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<CustomerDTO> getById(Long id) {
        return customerRepository.findById(id).map(CustomerMapper.INSTANCE::toDto);
    }
}
