package com.bp.ensayo.ct1.controller;

import com.bp.ensayo.ct1.domain.dto.Transaction;
import com.bp.ensayo.ct1.domain.dto.TransactionDTO;
import com.bp.ensayo.ct1.domain.dto.TransferDTO;
import com.bp.ensayo.ct1.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Transactions", description = "Gestionar transacciones")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Realizar un depósito")
    @PostMapping("/credit")
    public ResponseEntity<Transaction> makeDeposit(@RequestBody @Valid Transaction data) {
        return ResponseEntity.ok(transactionService.makeDeposit(data));
    }

    @Operation(summary = "Realizar un retiro")
    @PostMapping("/debit")
    public ResponseEntity<Transaction> makeWithdrawal(@RequestBody @Valid Transaction data) {
        return ResponseEntity.ok(transactionService.makeWithdrawal(data));
    }

    @Operation(summary = "Realizar una transferencia")
    @PostMapping("/transfer")
    public ResponseEntity<TransferDTO> makeTransfer(@RequestBody @Valid TransferDTO data) {
        return ResponseEntity.ok(transactionService.makeTransfer(data));
    }

    @Operation(summary = "Obtener resumen de transacciones")
    @GetMapping("/summary/{account-number}")
    public ResponseEntity<List<TransactionDTO>> getSummary(@PathVariable("account-number") String accountNumber) {
        return ResponseEntity.ok(transactionService.getSummary(accountNumber));
    }
}