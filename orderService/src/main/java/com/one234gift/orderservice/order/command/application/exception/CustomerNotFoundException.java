package com.one234gift.orderservice.order.command.application.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        super("해당 고객이 존재하지 않습니다.");
    }
}
