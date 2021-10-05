package com.one234gift.orderservice.command.application.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        super("해당 고객이 존재하지 않습니다.");
    }
}
