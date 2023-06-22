package com.bp.ensayo.service.impl;

import com.bp.ensayo.service.FunctionalInterfacesService;
import com.bp.ensayo.service.ifun.FunctionalInterfaceMath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FunctionalInterfacesServiceImpl implements FunctionalInterfacesService {
    @Override
    public Double operation() {
        FunctionalInterfaceMath functionalInterface = (p1, p2, isPercentage) -> p1 + p2;
        return functionalInterface.operation(50, 40, false);
    }

    @Override
    public String defaultMethod() {
        FunctionalInterfaceMath functionalInterface = (p1, p2, isPercentage) -> {
            double total = p1 + p2;
            if (isPercentage) {
                total = total * 100;
            }
            return total;
        };
        return functionalInterface.defaultMethod();
    }
}
