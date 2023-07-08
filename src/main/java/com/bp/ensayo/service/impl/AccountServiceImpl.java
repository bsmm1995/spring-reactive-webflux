package com.bp.ensayo.service.impl;

import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.repository.entity.AccountEntity;
import com.bp.ensayo.service.AccountService;
import com.bp.ensayo.service.dto.AccountDTO;
import com.bp.ensayo.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
        return accountRepository.findById(id)
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(employee -> accountRepository.delete(employee).subscribe()).then();
    }

    @Override
    public Flux<AccountDTO> getAll(PageRequest pageRequest) {
        return accountRepository.findAll(pageRequest.getSort())
                .map(AccountMapper.INSTANCE::toDto)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<AccountDTO> getById(Long id) {
        return accountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la cuenta con ID " + id)))
                .map(AccountMapper.INSTANCE::toDto);
    }
}
