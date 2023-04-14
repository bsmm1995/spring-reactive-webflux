package com.bp.ensayo.ct1.service;

import com.bp.ensayo.ct1.domain.dto.Transaction;
import com.bp.ensayo.ct1.domain.dto.TransactionDTO;
import com.bp.ensayo.ct1.domain.dto.TransferDTO;

import java.util.List;

public interface TransactionService {
    Transaction makeDeposit(Transaction data);

    Transaction makeWithdrawal(Transaction data);

    TransferDTO makeTransfer(TransferDTO data);

    List<TransactionDTO> getSummary(String accountNumber);
}
