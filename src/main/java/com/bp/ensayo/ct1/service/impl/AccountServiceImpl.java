package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.domain.dto.AccountDTO;
import com.bp.ensayo.ct1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDTO save(AccountDTO data) {
        return null;
    }

    @Override
    public AccountDTO update(long id, AccountDTO data) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<AccountDTO> getAll() {
        return null;
    }

    @Override
    public AccountDTO getById(long id) {
        return null;
    }
}
