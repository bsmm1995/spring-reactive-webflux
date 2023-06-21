package com.bp.ensayo.controller;

import com.bp.ensayo.service.WebService;
import com.bp.ensayo.service.dto.ProductDTO;
import com.bp.ensayo.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Web services", description = "Consumir servicios externos")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services")
public class WebController {
    private final WebService webService;

    @Operation(summary = "Obtener los productos.")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(webService.getProducts());
    }

    @Operation(summary = "Obtener los productos.")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(webService.getUsers());
    }
}