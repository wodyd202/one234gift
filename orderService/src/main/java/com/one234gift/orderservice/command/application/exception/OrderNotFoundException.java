package com.one234gift.orderservice.command.application.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(){
        super("해당 주문 정보가 존재하지 않습니다.");
    }
}
