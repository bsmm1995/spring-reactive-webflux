package com.bp.ensayo.helper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Factory {
    private final PlatziFakeApiClient platziFakeApiClient;
    @Value("${external.services.fakeapi.platzi}")
    private String url;

    public Factory(PlatziFakeApiClient platziFakeApiClient) {
        this.platziFakeApiClient = platziFakeApiClient;
    }
}
