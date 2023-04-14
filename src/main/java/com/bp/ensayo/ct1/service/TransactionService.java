package com.bp.ensayo.ct1.service;

import com.bp.ensayo.ct1.domain.dto.TransactionDTO;
import com.bp.ensayo.ct1.domain.dto.TransferDTO;

public interface TransactionService {
    TransactionDTO makeDeposit(TransactionDTO data);

    TransactionDTO makeWithdrawal(TransactionDTO data);

    TransferDTO makeTransfer(TransferDTO data);
}
