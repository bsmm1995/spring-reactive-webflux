package com.bp.ensayo.ct1.exception;

public class AccountException extends RuntimeException {
    public AccountException(String sms) {
        super(sms);
    }
}
