package com.bp.ensayo.ct1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer", description = "Servicios para administrar clientes.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {
}