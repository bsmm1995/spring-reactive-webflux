package com.bp.ensayo.ct1.controller;

import com.bp.ensayo.ct1.domain.dto.TransactionDTO;
import com.bp.ensayo.ct1.domain.dto.TransferDTO;
import com.bp.ensayo.ct1.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Customer", description = "Servicios para administrar clientes.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Realizar un depósito")
    @PostMapping("/credit")
    public ResponseEntity<TransactionDTO> makeDeposit(@RequestBody @Valid TransactionDTO data) {
        return ResponseEntity.ok(transactionService.makeDeposit(data));
    }

    @Operation(summary = "Realizar un retiro")
    @PostMapping("/debit")
    public ResponseEntity<TransactionDTO> makeWithdrawal(@RequestBody @Valid TransactionDTO data) {
        return ResponseEntity.ok(transactionService.makeWithdrawal(data));
    }

    @Operation(summary = "Realizar una transferencia")
    @PostMapping("/transfer")
    public ResponseEntity<TransferDTO> makeTransfer(@RequestBody @Valid TransferDTO data) {
        return ResponseEntity.ok(transactionService.makeTransfer(data));
    }
}