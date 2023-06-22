package com.bp.ensayo.service.ifun;

@FunctionalInterface
public interface FunctionalInterfaceMath {
    double operation(double param1, double param2, boolean isPercentage);

    default String defaultMethod() {
        return "This is the default method";
    }
}
