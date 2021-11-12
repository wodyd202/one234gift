package com.one234gift.userservice.domain.exception;

public class AlreadyLeaveException extends RuntimeException {
    public AlreadyLeaveException(String msg) {
        super(msg);
    }
}
