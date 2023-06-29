package com.bp.ensayo.service.impl;

import com.bp.ensayo.exception.AccountException;
import com.bp.ensayo.repository.AccountRepository;
import com.bp.ensayo.repository.TransactionRepository;
import com.bp.ensayo.repository.entity.AccountEntity;
import com.bp.ensayo.repository.entity.TransactionEntity;
import com.bp.ensayo.repository.enums.AccountStatus;
import com.bp.ensayo.repository.enums.AccountType;
import com.bp.ensayo.service.TransactionService;
import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransferDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceImplTest {
    @MockBean
    AccountRepository accountRepositoryMock;
    @MockBean
    TransactionRepository transactionRepositoryMock;
    @Autowired
    TransactionService transactionService;

    @Bean
    public TransactionService geTransactionService() {
        return new TransactionServiceImpl(accountRepositoryMock, transactionRepositoryMock);
    }

    @Test
    void makeDeposit() {
        String cuenta = "123-1";
        AccountEntity account = new AccountEntity();
        account.setId(1L);
        account.setAccountNumber(cuenta);
        account.setAmount(new BigDecimal(100));
        account.setType(AccountType.SAVINGS);
        account.setStatus(AccountStatus.ACTIVE);

        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));

        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Mono.just(account));
        when(accountRepositoryMock.save(any())).thenReturn(Mono.just(account));
        when(transactionRepositoryMock.save(any())).thenReturn(Mono.just(new TransactionEntity()));

        StepVerifier
                .create(transactionService.makeDeposit(transaction))
                .assertNext(trans -> {
                    assertThat(trans.getAmount().equals(transaction.getAmount())).isEqualTo(Boolean.TRUE);
                    assertThat(trans.getAccountNumber().equals(cuenta)).isEqualTo(Boolean.TRUE);
                })
                .verifyComplete();
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

        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Mono.just(account));
        when(accountRepositoryMock.save(any())).thenReturn(Mono.just(account));
        when(transactionRepositoryMock.save(any())).thenReturn(Mono.just(new TransactionEntity()));

        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));

        StepVerifier
                .create(transactionService.makeWithdrawal(transaction))
                .assertNext(trans -> {
                    assertThat(trans.getAmount().equals(transaction.getAmount())).isEqualTo(Boolean.TRUE);
                    assertThat(trans.getAccountNumber().equals(cuenta)).isEqualTo(Boolean.TRUE);
                })
                .verifyComplete();
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

        when(accountRepositoryMock.findByAccountNumber(cuenta)).thenReturn(Mono.just(account));
        when(accountRepositoryMock.save(any())).thenReturn(Mono.just(account));
        when(transactionRepositoryMock.save(any())).thenReturn(Mono.just(new TransactionEntity()));

        Transaction transaction = new Transaction(cuenta, new BigDecimal(50));

        StepVerifier
                .create(transactionService.makeWithdrawal(transaction))
                .expectErrorMatches(throwable -> throwable instanceof AccountException &&
                        throwable.getMessage().equals("Saldo insuficiente. Saldo actual " + account.getAmount())
                ).verify();
    }

    @Test
    void makeTransferMismaCuenta() {
        String accountNumber = "1234";
        TransferDTO transaction = new TransferDTO();
        transaction.setAccountNumberOrigin(accountNumber);
        transaction.setAccountNumberDestination(accountNumber);
        transaction.setAmount(new BigDecimal(120));

        StepVerifier
                .create(transactionService.makeTransfer(transaction))
                .expectErrorMatches(throwable -> throwable instanceof AccountException &&
                        throwable.getMessage().equals("No se puede realizar transferencia entre la misma cuenta.")
                ).verify();
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

        when(accountRepositoryMock.findByAccountNumber(accountNumber)).thenReturn(Mono.just(accountOrigen));
        when(accountRepositoryMock.save(any())).thenReturn(Mono.just(accountOrigen));
        when(transactionRepositoryMock.save(any())).thenReturn(Mono.just(new TransactionEntity()));

        StepVerifier
                .create(transactionService.makeTransfer(transaction))
                .expectErrorMatches(throwable -> throwable instanceof AccountException &&
                        throwable.getMessage().equals("Saldo insuficiente. Saldo actual " + accountOrigen.getAmount())
                ).verify();
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

        when(accountRepositoryMock.findByAccountNumber(accountNumber)).thenReturn(Mono.just(accountOrigen));
        when(accountRepositoryMock.findByAccountNumber(transaction.getAccountNumberDestination())).thenReturn(Mono.just(accountDestination));
        when(accountRepositoryMock.save(any())).thenReturn(Mono.just(accountOrigen));
        when(transactionRepositoryMock.save(any())).thenReturn(Mono.just(new TransactionEntity()));

        StepVerifier
                .create(transactionService.makeTransfer(transaction))
                .expectErrorMatches(throwable -> throwable instanceof AccountException &&
                        throwable.getMessage().equals("La cuenta " + transaction.getAccountNumberDestination() + " no se encuentra activa.")
                ).verify();
    }
}