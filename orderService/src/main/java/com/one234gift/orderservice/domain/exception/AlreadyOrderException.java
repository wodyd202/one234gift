package com.one234gift.orderservice.domain.exception;

public class AlreadyOrderException extends RuntimeException {
    public AlreadyOrderException(){
        super("이미 주문이 완료되었습니다.");
    }
}
