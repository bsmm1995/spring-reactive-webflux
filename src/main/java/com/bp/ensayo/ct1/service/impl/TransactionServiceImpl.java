package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.domain.dto.Transaction;
import com.bp.ensayo.ct1.domain.dto.TransactionDTO;
import com.bp.ensayo.ct1.domain.dto.TransferDTO;
import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import com.bp.ensayo.ct1.domain.entity.TransactionEntity;
import com.bp.ensayo.ct1.domain.enu.AccountStatus;
import com.bp.ensayo.ct1.domain.enu.TransactionType;
import com.bp.ensayo.ct1.exception.AccountException;
import com.bp.ensayo.ct1.repository.AccountRepository;
import com.bp.ensayo.ct1.repository.TransactionRepository;
import com.bp.ensayo.ct1.service.TransactionService;
import com.bp.ensayo.ct1.service.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction makeDeposit(Transaction data) {
        AccountEntity account = getAccountEntityByNumber(data.getAccountNumber());
        account.setAmount(account.getAmount().add(data.getAmount()));
        accountRepository.save(account);
        log.info("Se guardó el deposito");
        saveTransaction(account, data.getAmount(), TransactionType.CREDIT);
        return data;
    }

    @Override
    @Transactional
    public Transaction makeWithdrawal(Transaction data) {
        AccountEntity account = getAccountEntityByNumber(data.getAccountNumber());
        if (account.getAmount().compareTo(data.getAmount()) < 0) {
            throw new AccountException("Saldo insuficiente. Saldo actual " + account.getAmount());
        }
        account.setAmount(account.getAmount().subtract(data.getAmount()));
        accountRepository.save(account);
        log.info("Se guardó el retiro");
        saveTransaction(account, data.getAmount(), TransactionType.DEBIT);
        return data;
    }

    @Override
    @Transactional
    public TransferDTO makeTransfer(TransferDTO data) {
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
        return data;
    }

    @Override
    public List<TransactionDTO> getSummary(String accountNumber) {
        AccountEntity account = getAccountEntityByNumber(accountNumber);
        return transactionRepository.findAllByAccount(account).stream().map(TransactionMapper.INSTANCE::toDto).toList();
    }

    private AccountEntity getAccountEntityByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("No existe la cuenta con numero " + accountNumber));
    }

    private void saveTransaction(AccountEntity account, BigDecimal amount, TransactionType type) {
        TransactionEntity credit = TransactionEntity.builder().transactionType(type).amount(amount).account(account).build();
        transactionRepository.save(credit);
        log.info("Se guardó la transacción. cuenta={}, tipo={}", account.getAccountNumber(), type);
    }
}
