package com.bp.ensayo.service.impl;

import com.bp.ensayo.domain.entity.AccountEntity;
import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.service.AccountService;
import com.bp.ensayo.service.dto.AccountDTO;
import com.bp.ensayo.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Mono<AccountDTO> save(AccountDTO data) {
        AccountEntity entity = AccountMapper.INSTANCE.toEntity(data);
        return accountRepository.save(entity).map(AccountMapper.INSTANCE::toDto);
    }

    @Override
    public Mono<AccountDTO> update(Long id, AccountDTO data) {
        return accountRepository.findById(id)
                .flatMap(entity -> {
                    entity.setType(data.getType());
                    entity.setStatus(data.getStatus());
                    return accountRepository.save(entity);
                })
                .map(AccountMapper.INSTANCE::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public Flux<AccountDTO> getAll(PageRequest pageRequest) {
        return accountRepository.findAll(pageRequest.getSort())
                .map(AccountMapper.INSTANCE::toDto)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<AccountDTO> getById(Long id) {
        return accountRepository.findById(id).map(AccountMapper.INSTANCE::toDto);
    }
}