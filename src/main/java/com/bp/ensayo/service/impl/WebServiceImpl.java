package com.bp.ensayo.service.impl;

import com.bp.ensayo.helper.Factory;
import com.bp.ensayo.service.WebService;
import com.bp.ensayo.service.dto.CategoryDTO;
import com.bp.ensayo.service.dto.ProductDTO;
import com.bp.ensayo.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebServiceImpl implements WebService {
    private final Factory factory;

    @Override
    public List<ProductDTO> getProducts() {
        return factory.getPlatziFakeApiClient().getProducts();
    }

    @Override
    public List<UserDTO> getUsers() {
        ResponseEntity<List<UserDTO>> response = factory.getRestTemplate()
                .exchange(factory.getUrl().concat("/users"), HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return response.getBody();
    }

    @Override
    public List<CategoryDTO> getCategories() {
        WebClient webClient = WebClient.builder()
                .baseUrl(factory.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Mono<List<CategoryDTO>> response = webClient.get().uri("/categories").retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        return response.block();
    }
}
