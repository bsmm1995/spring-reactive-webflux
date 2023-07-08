package com.bp.ensayo.service.impl;

import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.repository.TransactionRepository;
import com.bp.ensayo.repository.entity.TransactionEntity;
import com.bp.ensayo.repository.enums.AccountStatus;
import com.bp.ensayo.repository.enums.TransactionType;
import com.bp.ensayo.service.TransactionService;
import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.service.dto.TransferDTO;
import com.bp.ensayo.service.mapper.TransactionMapper;
import com.bp.ensayo.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Mono<Transaction> makeDeposit(Transaction data) {
        return accountRepository.findByAccountNumber(data.getAccountNumber())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.ACCOUNT_NOT_FOUND + data.getAccountNumber())))
                .flatMap(account -> {
                    account.setAmount(account.getAmount().add(data.getAmount()));
                    return accountRepository.save(account);
                }).doOnSuccess(e -> log.info("Se guardó el deposito."))
                .flatMap(account -> {
                    TransactionEntity credit = TransactionEntity.builder().transactionType(TransactionType.CREDIT).amount(data.getAmount()).accountId(account.getId()).build();
                    return transactionRepository.save(credit);
                })
                .doOnSuccess(e -> log.info("Se guardó la transacción de depósito."))
                .map(transaction -> data);
    }

    @Override
    @Transactional
    public Mono<Transaction> makeWithdrawal(Transaction data) {
        return accountRepository.findByAccountNumber(data.getAccountNumber())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.ACCOUNT_NOT_FOUND + data.getAccountNumber())))
                .doOnSuccess(account -> {
                    if (account.getAmount().compareTo(data.getAmount()) < 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente. Saldo actual " + account.getAmount());
                    }
                })
                .flatMap(account -> {
                    account.setAmount(account.getAmount().subtract(data.getAmount()));
                    return accountRepository.save(account);
                }).doOnSuccess(e -> log.info("Se hizo el débito."))
                .flatMap(account -> {
                    TransactionEntity credit = TransactionEntity.builder().transactionType(TransactionType.DEBIT).amount(data.getAmount()).accountId(account.getId()).build();
                    return transactionRepository.save(credit);
                })
                .doOnSuccess(e -> log.info("Se guardó la transacción de retiro."))
                .map(transaction -> data);
    }

    @Override
    @Transactional
    public Mono<TransferDTO> makeTransfer(TransferDTO data) {
        if (data.getAccountNumberOrigin().equals(data.getAccountNumberDestination())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede realizar transferencia entre la misma cuenta.");
        }
        return accountRepository.findByAccountNumber(data.getAccountNumberOrigin())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.ACCOUNT_NOT_FOUND + data.getAccountNumberOrigin())))
                .doOnSuccess(accountOrigin -> {
                    if (accountOrigin.getAmount().compareTo(data.getAmount()) < 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente. Saldo actual " + accountOrigin.getAmount());
                    }
                })
                .flatMap(accountOrigin -> {
                    accountOrigin.setAmount(accountOrigin.getAmount().subtract(data.getAmount()));
                    return accountRepository.save(accountOrigin);
                })
                .flatMap(accountOrigin -> {
                    TransactionEntity credit = TransactionEntity.builder().transactionType(TransactionType.DEBIT).amount(data.getAmount()).accountId(accountOrigin.getId()).build();
                    return transactionRepository.save(credit);
                })
                .doOnSuccess(e -> log.info("Se guardó el debito a la cuenta origen."))
                .flatMap(transaction ->
                        accountRepository.findByAccountNumber(data.getAccountNumberDestination())
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la cuenta número " + data.getAccountNumberDestination())))
                                .doOnSuccess(accountDestination -> {
                                    if (accountDestination.getStatus().equals(AccountStatus.INACTIVE)) {
                                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cuenta " + data.getAccountNumberDestination() + " no se encuentra activa.");
                                    }
                                })
                                .flatMap(accountDestination -> {
                                    accountDestination.setAmount(accountDestination.getAmount().add(data.getAmount()));
                                    return accountRepository.save(accountDestination);
                                })
                                .flatMap(accountDestination -> {
                                    TransactionEntity credit = TransactionEntity.builder().transactionType(TransactionType.CREDIT).amount(data.getAmount()).accountId(accountDestination.getId()).build();
                                    return transactionRepository.save(credit);
                                })
                                .doOnSuccess(op -> log.info("Se guardó la acreditación a la cuenta destino."))
                )
                .map(transaction -> data);
    }

    @Override
    public Flux<TransactionDTO> getSummary(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .doOnNext(e -> log.info("Report of: {}", e))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la cuenta número " + accountNumber)))
                .flatMapMany(e -> transactionRepository.findAllByAccountId(e.getId()).map(TransactionMapper.INSTANCE::toDto));
    }
}
