package com.bp.ensayo.ct1.service.impl;

import com.bp.ensayo.ct1.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String test(UUID id) {
        log.info("Service test: {}", id);
        return id.toString();
    }
}
