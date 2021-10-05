package com.one234gift.orderservice.domain.exception;

public class EnableOrderInfoChangeException extends RuntimeException {
    public EnableOrderInfoChangeException(){
        super("승인 대기중인 주문만 주문 정보를 변경할 수 있습니다.");
    }
}
