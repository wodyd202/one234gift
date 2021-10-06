package com.one234gift.saleshistoryservice.command.application.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        super("고객 정보가 존재하지 않습니다.");
    }
}
