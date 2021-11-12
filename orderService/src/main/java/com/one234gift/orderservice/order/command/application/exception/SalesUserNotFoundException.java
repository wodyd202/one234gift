package com.one234gift.orderservice.order.command.application.exception;

public class SalesUserNotFoundException extends RuntimeException{
    public SalesUserNotFoundException(){
        super("해당 영업자가 존재하지 않습니다.");
    }
}
