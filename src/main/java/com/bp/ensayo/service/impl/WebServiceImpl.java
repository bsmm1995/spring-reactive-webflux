package com.bp.ensayo.service.impl;

import com.bp.ensayo.helper.FactoryClient;
import com.bp.ensayo.service.WebService;
import com.bp.ensayo.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebServiceImpl implements WebService {
    private final FactoryClient factoryClient;

    @Override
    public List<ProductDTO> getProducts() {
        return factoryClient.getPlatziFakeApiClient().getProducts();
    }
}
