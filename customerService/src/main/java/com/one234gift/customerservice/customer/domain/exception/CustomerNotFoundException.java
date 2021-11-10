package com.one234gift.customerservice.customer.domain.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        super("해당 고객이 존재하지 않습니다. 고객 고유 번호를 다시 확인해주세요.");
    }
}
