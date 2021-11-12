package com.one234gift.userservice.domain.exception;

public class AlreadyWorkingException extends RuntimeException {
    public AlreadyWorkingException(String msg) {
        super(msg);
    }
}
