package com.bp.ensayo.service;

import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.service.dto.TransferDTO;

import java.util.List;

public interface TransactionService {
    Transaction makeDeposit(Transaction data);

    Transaction makeWithdrawal(Transaction data);

    TransferDTO makeTransfer(TransferDTO data);

    List<TransactionDTO> getSummary(String accountNumber);
}
