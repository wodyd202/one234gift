package com.one234gift.customerservice.customer.command.application.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(){
        super("해당 지역정보가 존재하지 않습니다. 지역정보를 다시 확인해주세요.");
    }
}
