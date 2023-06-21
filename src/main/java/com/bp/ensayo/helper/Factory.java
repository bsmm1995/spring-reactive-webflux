package com.bp.ensayo.helper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Getter
public class Factory {
    private final PlatziFakeApiClient platziFakeApiClient;
    private final RestTemplate restTemplate;
    @Value("${external.services.fakeapi.platzi}")
    private String url;

    public Factory(PlatziFakeApiClient platziFakeApiClient, RestTemplateBuilder builder) {
        this.platziFakeApiClient = platziFakeApiClient;
        this.restTemplate = builder.build();
    }
}
