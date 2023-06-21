package com.bp.ensayo.ct1.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class FactoryClient {
    private final PlatziFakeApiClient platziFakeApiClient;
}
