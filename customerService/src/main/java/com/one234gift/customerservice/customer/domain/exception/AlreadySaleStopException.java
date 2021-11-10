package com.one234gift.customerservice.customer.domain.exception;

public class AlreadySaleStopException extends RuntimeException {
    public AlreadySaleStopException(){
        super("이미 영업 중단되어있는 고객입니다. 고객을 다시 확인해주세요.");
    }
}
