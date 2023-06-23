package com.bp.ensayo.service;

import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.service.dto.TransferDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<Transaction> makeDeposit(Transaction data);

    Mono<Transaction> makeWithdrawal(Transaction data);

    Mono<TransferDTO> makeTransfer(TransferDTO data);

    Flux<TransactionDTO> getSummary(String accountNumber);
}
