package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.domain.dto.AccountDTO;
import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import com.bp.ensayo.ct1.repository.AccountRepository;
import com.bp.ensayo.ct1.service.AccountService;
import com.bp.ensayo.ct1.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDTO save(AccountDTO data) {
        AccountEntity entity = AccountMapper.INSTANCE.toEntity(data);
        return AccountMapper.INSTANCE.toDto(accountRepository.save(entity));
    }

    @Override
    public AccountDTO update(long id, AccountDTO data) {
        AccountEntity entity = getAccountEntity(id);
        entity.setType(data.getType());
        entity.setStatus(data.getStatus());
        return AccountMapper.INSTANCE.toDto(accountRepository.save(entity));
    }

    @Override
    public void delete(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream().map(AccountMapper.INSTANCE::toDto).toList();
    }

    @Override
    public AccountDTO getById(long id) {
        AccountEntity entity = getAccountEntity(id);
        return AccountMapper.INSTANCE.toDto(entity);
    }

    private AccountEntity getAccountEntity(long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la cuenta con ID " + id));
    }
}
