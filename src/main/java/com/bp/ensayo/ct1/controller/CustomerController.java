package com.bp.ensayo.ct1.controller;

import com.bp.ensayo.ct1.domain.dto.CustomerDTO;
import com.bp.ensayo.ct1.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Customer", description = "Servicios para administrar clientes.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Obtener cliente por ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        log.info("getTotalScore caseId={}", id);
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerDTO data) {
        return ResponseEntity.ok(customerService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody @Valid CustomerDTO data) {
        return ResponseEntity.ok(customerService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("getTotalScore caseId={}", id);
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}