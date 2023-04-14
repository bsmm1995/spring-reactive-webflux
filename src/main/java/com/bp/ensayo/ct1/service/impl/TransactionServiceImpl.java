package com.bp.ensayo.ct1.service.impl;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public TransactionDTO makeDeposit(TransactionDTO data) {
        AccountEntity account = getAccountEntity(data.getAccountNumber());
        account.setAmount(account.getAmount().add(data.getAmount()));
        accountRepository.save(account);

        saveTransaction(account, data.getAmount(), TransactionType.CREDIT);
        return data;
    }

    @Override
    @Transactional
    public TransactionDTO makeWithdrawal(TransactionDTO data) {
        AccountEntity account = getAccountEntity(data.getAccountNumber());
        if (account.getAmount().compareTo(data.getAmount()) < 0) {
            throw new AccountException("Saldo insuficiente. Saldo actual " + account.getAmount());
        }
        account.setAmount(account.getAmount().subtract(data.getAmount()));
        accountRepository.save(account);

        saveTransaction(account, data.getAmount(), TransactionType.DEBIT);
        return data;
    }

    @Override
    @Transactional
    public TransferDTO makeTransfer(TransferDTO data) {
        if (data.getAccountNumberOrigin().equals(data.getAccountNumberDestination())) {
            throw new AccountException("No se puede realizar transferencia entre la misma cuenta.");
        }
        AccountEntity accountOrigen = getAccountEntity(data.getAccountNumberOrigin());
        AccountEntity accountDestination = getAccountEntity(data.getAccountNumberDestination());
        if (accountOrigen.getAmount().compareTo(data.getAmount()) < 0) {
            throw new AccountException("Saldo insuficiente. Saldo actual " + accountOrigen.getAmount());
        }

        if (accountDestination.getStatus().equals(AccountStatus.INACTIVE)) {
            throw new AccountException("La cuenta " + data.getAccountNumberDestination() + " no se encuentra activa.");
        }
        accountOrigen.setAmount(accountOrigen.getAmount().subtract(data.getAmount()));
        accountRepository.save(accountOrigen);

        accountDestination.setAmount(accountDestination.getAmount().add(data.getAmount()));
        accountRepository.save(accountDestination);

        saveTransaction(accountOrigen, data.getAmount(), TransactionType.DEBIT);
        saveTransaction(accountDestination, data.getAmount(), TransactionType.CREDIT);
        return data;
    }

    private AccountEntity getAccountEntity(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("No existe la cuenta con numero " + accountNumber));
    }

    private void saveTransaction(AccountEntity account, BigDecimal amount, TransactionType type) {
        TransactionEntity credit = TransactionEntity.builder().transactionType(type).amount(amount).account(account).build();
        transactionRepository.save(credit);
    }
}
