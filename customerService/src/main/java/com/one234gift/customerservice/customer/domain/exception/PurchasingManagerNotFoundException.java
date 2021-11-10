package com.one234gift.customerservice.customer.domain.exception;

public class PurchasingManagerNotFoundException extends RuntimeException {
    public PurchasingManagerNotFoundException(){
        super("해당 구매담당자가 존재하지 않습니다.");
    }
}
