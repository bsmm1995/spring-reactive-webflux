package com.bp.ensayo.helper;

import com.bp.ensayo.service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "platziFakeApiClient", url = "${external.services.fakeapi.platzi}/products")
public interface PlatziFakeApiClient {
    @GetMapping
    List<ProductDTO> getProducts();
}