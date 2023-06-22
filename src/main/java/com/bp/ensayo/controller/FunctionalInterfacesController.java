package com.bp.ensayo.controller;

import com.bp.ensayo.service.FunctionalInterfacesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Web services", description = "Consumir servicios externos")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/functional-interfaces")
public class FunctionalInterfacesController {
    private final FunctionalInterfacesService service;

    @Operation(summary = "Interface funcional con el metodo convert.")
    @GetMapping("/convert")
    public ResponseEntity<Double> operation() {
        return ResponseEntity.ok(service.operation());
    }

    @Operation(summary = "Interface funcional con el metodo default.")
    @GetMapping("/default")
    public ResponseEntity<String> defaultMethod() {
        return ResponseEntity.ok(service.defaultMethod());
    }

}