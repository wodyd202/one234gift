package com.one234gift.orderservice.order.domain.exception;

public class AlreadyDeliveryFinishedException extends RuntimeException {
    public AlreadyDeliveryFinishedException(){
        super("이미 배송이 완료된 주문입니다.");
    }
}
