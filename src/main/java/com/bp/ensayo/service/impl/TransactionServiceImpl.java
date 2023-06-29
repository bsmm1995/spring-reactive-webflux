package com.bp.ensayo.service.impl;

import com.bp.ensayo.exception.AccountException;
import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.repository.TransactionRepository;
import com.bp.ensayo.repository.entity.AccountEntity;
import com.bp.ensayo.repository.entity.TransactionEntity;
import com.bp.ensayo.repository.enums.AccountStatus;
import com.bp.ensayo.repository.enums.TransactionType;
import com.bp.ensayo.service.TransactionService;
import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.service.dto.TransferDTO;
import com.bp.ensayo.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Mono<Transaction> makeDeposit(Transaction data) {
        AccountEntity account = getAccountEntityByNumber(data.getAccountNumber());
        account.setAmount(account.getAmount().add(data.getAmount()));
        accountRepository.save(account);
        log.info("Se guardó el deposito");
        saveTransaction(account, data.getAmount(), TransactionType.CREDIT);
        return Mono.just(data);
    }

    @Override
    @Transactional
    public Mono<Transaction> makeWithdrawal(Transaction data) {
        AccountEntity account = getAccountEntityByNumber(data.getAccountNumber());
        if (account.getAmount().compareTo(data.getAmount()) < 0) {
            throw new AccountException("Saldo insuficiente. Saldo actual " + account.getAmount());
        }
        account.setAmount(account.getAmount().subtract(data.getAmount()));
        accountRepository.save(account);
        log.info("Se guardó el retiro");
        saveTransaction(account, data.getAmount(), TransactionType.DEBIT);
        return Mono.just(data);
    }

    @Override
    @Transactional
    public Mono<TransferDTO> makeTransfer(TransferDTO data) {
        if (data.getAccountNumberOrigin().equals(data.getAccountNumberDestination())) {
            throw new AccountException("No se puede realizar transferencia entre la misma cuenta.");
        }
        AccountEntity accountOrigen = getAccountEntityByNumber(data.getAccountNumberOrigin());
        if (accountOrigen.getAmount().compareTo(data.getAmount()) < 0) {
            throw new AccountException("Saldo insuficiente. Saldo actual " + accountOrigen.getAmount());
        }

        AccountEntity accountDestination = getAccountEntityByNumber(data.getAccountNumberDestination());
        if (accountDestination.getStatus().equals(AccountStatus.INACTIVE)) {
            throw new AccountException("La cuenta " + data.getAccountNumberDestination() + " no se encuentra activa.");
        }
        accountOrigen.setAmount(accountOrigen.getAmount().subtract(data.getAmount()));
        accountRepository.save(accountOrigen);
        log.info("Se guardó el debito en la cuenta origen");
        accountDestination.setAmount(accountDestination.getAmount().add(data.getAmount()));
        accountRepository.save(accountDestination);
        log.info("Se guardó el credito en la cuenta destino");
        saveTransaction(accountOrigen, data.getAmount(), TransactionType.DEBIT);
        saveTransaction(accountDestination, data.getAmount(), TransactionType.CREDIT);
        return Mono.just(data);
    }

    @Override
    public Flux<TransactionDTO> getSummary(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .doOnNext(e -> log.info("Report of: {}", e))
                .switchIfEmpty(Mono.error(new NoSuchElementException("No existe la cuenta número " + accountNumber)))
                .flatMapMany(e -> transactionRepository.findAllByAccountId(e.getId()).map(TransactionMapper.INSTANCE::toDto));
    }

    private AccountEntity getAccountEntityByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).blockOptional().orElseThrow(() -> new NoSuchElementException("No existe la cuenta con numero " + accountNumber));
    }

    private void saveTransaction(AccountEntity account, BigDecimal amount, TransactionType type) {
        TransactionEntity credit = TransactionEntity.builder().transactionType(type).amount(amount).accountId(account.getId()).build();
        transactionRepository.save(credit);
        log.info("Se guardó la transacción. cuenta={}, tipo={}", account.getAccountNumber(), type);
    }
}
