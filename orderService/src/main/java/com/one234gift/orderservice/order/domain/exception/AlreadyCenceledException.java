package com.one234gift.orderservice.order.domain.exception;

public class AlreadyCenceledException extends RuntimeException {
    public AlreadyCenceledException(){
        super("이미 취소된 주문입니다.");
    }
}
