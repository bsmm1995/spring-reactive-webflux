package com.bp.ensayo.ct1.controller;

import com.bp.ensayo.ct1.domain.dto.AccountDTO;
import com.bp.ensayo.ct1.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account", description = "Servicios para administrar cuentas.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Obtener cuenta por ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        log.info("getTotalScore caseId={}", id);
        return ResponseEntity.ok(accountService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody @Valid AccountDTO data) {
        return ResponseEntity.ok(accountService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @RequestBody @Valid AccountDTO data) {
        return ResponseEntity.ok(accountService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("getTotalScore caseId={}", id);
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}