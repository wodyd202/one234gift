package com.one234gift.customerservice.domain.exception;

public class AlreadySaleException extends RuntimeException {
    public AlreadySaleException(){
        super("이미 영업중인 고객입니다.");
    }
}
