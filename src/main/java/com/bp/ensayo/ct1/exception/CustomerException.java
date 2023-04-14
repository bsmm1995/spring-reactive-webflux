package com.bp.ensayo.ct1.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String sms) {
        super(sms);
    }
}
