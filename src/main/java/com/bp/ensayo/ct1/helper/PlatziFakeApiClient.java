package com.bp.ensayo.ct1.helper;

import com.bp.ensayo.ct1.service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "platziFakeApiClient", url = "${external.services.fakeapi.platzi}/products")
public interface PlatziFakeApiClient {
    @GetMapping
    List<ProductDTO> getProducts();
}
