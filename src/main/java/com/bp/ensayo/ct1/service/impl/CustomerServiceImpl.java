package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.domain.dto.CustomerDTO;
import com.bp.ensayo.ct1.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO save(CustomerDTO data) {
        return null;
    }

    @Override
    public CustomerDTO update(long id, CustomerDTO data) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<CustomerDTO> getAll() {
        return null;
    }

    @Override
    public CustomerDTO getById(long id) {
        return null;
    }
}
