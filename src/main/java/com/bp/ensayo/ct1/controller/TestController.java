package com.bp.ensayo.ct1.controller;

import com.bp.ensayo.ct1.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Servicios TEST", description = "Servicios para ensayo de evaluacion.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/test-api")
public class TestController {
    private final TestService testService;

    @Operation(summary = "Test endpoint.")
    @GetMapping("/{id}")
    public ResponseEntity<String> getTotalScore(@PathVariable UUID id) {
        log.info("getTotalScore caseId={}", id);
        return ResponseEntity.ok(testService.test(id));
    }
}