package com.bp.ensayo.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String sms) {
        super(sms);
    }
}
