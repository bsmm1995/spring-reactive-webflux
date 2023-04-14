package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.domain.dto.Transaction;
import com.bp.ensayo.ct1.domain.entity.AccountEntity;
import com.bp.ensayo.ct1.domain.enu.AccountStatus;
import com.bp.ensayo.ct1.domain.enu.AccountType;
import com.bp.ensayo.ct1.exception.AccountException;
import com.bp.ensayo.ct1.repository.AccountRepository;
import com.bp.ensayo.ct1.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransactionServiceImplTest {
    @Mock
    AccountRepository accountRepositoryMock;
    @Mock
    TransactionRepository transactionRepositoryMock;
    @InjectMocks
    TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void makeDeposit() {
        String cuenta = "1234";
        AccountEntity account = new AccountEntity();
        account.setId(1L);
        account.setAccountNumber(cuenta);
        account.setAmount(new BigDecimal(100));
        account.setType(AccountType.SAVINGS);
        account.setStatus(AccountStatus.ACTIVE);
        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Optional.of(account));
        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));
        Transaction body = transactionService.makeDeposit(transaction);
        assertNotNull(body);
        assertEquals(body.getAmount(), transaction.getAmount());
    }

    @Test
    void makeWithdrawal() {
        String cuenta = "1234";
        AccountEntity account = new AccountEntity();
        account.setId(1L);
        account.setAccountNumber(cuenta);
        account.setAmount(new BigDecimal(100));
        account.setType(AccountType.SAVINGS);
        account.setStatus(AccountStatus.ACTIVE);
        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Optional.of(account));
        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));
        Transaction body = transactionService.makeWithdrawal(transaction);
        assertNotNull(body);
        assertEquals(body.getAmount(), transaction.getAmount());
    }

    @Test
    void makeWithdrawalSaldoInsuficiente() {
        String cuenta = "1234";
        AccountEntity account = new AccountEntity();
        account.setId(1L);
        account.setAccountNumber(cuenta);
        account.setAmount(new BigDecimal(5));
        account.setType(AccountType.SAVINGS);
        account.setStatus(AccountStatus.ACTIVE);
        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Optional.of(account));
        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));
        Throwable exception = assertThrows(AccountException.class, () -> transactionService.makeWithdrawal(transaction));
        assertEquals("Saldo insuficiente. Saldo actual " + account.getAmount(), exception.getMessage());
    }

    @Test
    void makeTransfer() {
    }
}