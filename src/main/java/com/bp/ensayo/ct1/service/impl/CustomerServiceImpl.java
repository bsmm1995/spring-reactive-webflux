package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.service.dto.CustomerDTO;
import com.bp.ensayo.ct1.domain.entity.CustomerEntity;
import com.bp.ensayo.ct1.repository.CustomerRepository;
import com.bp.ensayo.ct1.service.CustomerService;
import com.bp.ensayo.ct1.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerDTO data) {
        CustomerEntity entity = CustomerMapper.INSTANCE.toEntity(data);
        return CustomerMapper.INSTANCE.toDto(customerRepository.save(entity));
    }

    @Override
    public CustomerDTO update(long id, CustomerDTO data) {
        CustomerEntity entity = getCustomerEntity(id);
        entity.setName(data.getName());
        entity.setLastname(data.getLastname());
        entity.setIdentificationType(data.getIdentificationType());
        return CustomerMapper.INSTANCE.toDto(customerRepository.save(entity));
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(CustomerMapper.INSTANCE::toDto).toList();
    }

    @Override
    public CustomerDTO getById(long id) {
        CustomerEntity entity = getCustomerEntity(id);
        return CustomerMapper.INSTANCE.toDto(entity);
    }

    private CustomerEntity getCustomerEntity(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el cliente con ID " + id));
    }
}
