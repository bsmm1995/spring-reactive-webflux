package com.bp.ensayo.exception;

public class AccountException extends RuntimeException {
    public AccountException(String sms) {
        super(sms);
    }
}
