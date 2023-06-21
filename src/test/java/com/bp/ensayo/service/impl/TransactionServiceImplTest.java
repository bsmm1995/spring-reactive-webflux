package com.bp.ensayo.service.impl;

import com.bp.ensayo.domain.entity.AccountEntity;
import com.bp.ensayo.domain.enums.AccountStatus;
import com.bp.ensayo.domain.enums.AccountType;
import com.bp.ensayo.exception.AccountException;
import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.repository.TransactionRepository;
import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransferDTO;
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
    void makeTransferMismaCuenta() {
        String accountNumber = "1234";
        TransferDTO transaction = new TransferDTO();
        transaction.setAccountNumberOrigin(accountNumber);
        transaction.setAccountNumberDestination(accountNumber);
        transaction.setAmount(new BigDecimal(120));

        Throwable exception = assertThrows(AccountException.class, () -> transactionService.makeTransfer(transaction));
        assertEquals("No se puede realizar transferencia entre la misma cuenta.", exception.getMessage());
    }

    @Test
    void makeTransferSaldoInsuficiente() {
        String accountNumber = "1234";

        TransferDTO transaction = new TransferDTO();
        transaction.setAccountNumberOrigin(accountNumber);
        transaction.setAccountNumberDestination("1235");
        transaction.setAmount(new BigDecimal(120));

        AccountEntity accountOrigen = new AccountEntity();
        accountOrigen.setId(1L);
        accountOrigen.setAccountNumber(accountNumber);
        accountOrigen.setAmount(new BigDecimal(5));
        accountOrigen.setType(AccountType.SAVINGS);
        accountOrigen.setStatus(AccountStatus.ACTIVE);

        when(accountRepositoryMock.findByAccountNumber(accountNumber)).thenReturn(Optional.of(accountOrigen));
        Throwable exception = assertThrows(AccountException.class, () -> transactionService.makeTransfer(transaction));
        assertEquals("Saldo insuficiente. Saldo actual " + accountOrigen.getAmount(), exception.getMessage());
    }

    @Test
    void makeTransferCuentaInactiva() {
        String accountNumber = "1234";

        TransferDTO transaction = new TransferDTO();
        transaction.setAccountNumberOrigin(accountNumber);
        transaction.setAccountNumberDestination("1235");
        transaction.setAmount(new BigDecimal(120));

        AccountEntity accountOrigen = new AccountEntity();
        accountOrigen.setId(1L);
        accountOrigen.setAccountNumber(accountNumber);
        accountOrigen.setAmount(new BigDecimal(5000));
        accountOrigen.setType(AccountType.SAVINGS);
        accountOrigen.setStatus(AccountStatus.ACTIVE);

        AccountEntity accountDestination = new AccountEntity();
        accountDestination.setId(1L);
        accountDestination.setAccountNumber(transaction.getAccountNumberDestination());
        accountDestination.setAmount(new BigDecimal(5));
        accountDestination.setType(AccountType.SAVINGS);
        accountDestination.setStatus(AccountStatus.INACTIVE);

        when(accountRepositoryMock.findByAccountNumber(accountNumber)).thenReturn(Optional.of(accountOrigen));
        when(accountRepositoryMock.findByAccountNumber(transaction.getAccountNumberDestination())).thenReturn(Optional.of(accountDestination));
        Throwable exception = assertThrows(AccountException.class, () -> transactionService.makeTransfer(transaction));
        assertEquals("La cuenta " + transaction.getAccountNumberDestination() + " no se encuentra activa.", exception.getMessage());
    }
}