package com.one234gift.orderservice.order.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(){
        super("해당 주문 정보가 존재하지 않습니다.");
    }
}
