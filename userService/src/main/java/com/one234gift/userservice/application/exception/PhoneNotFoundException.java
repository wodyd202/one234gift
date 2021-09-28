package com.one234gift.userservice.application.exception;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(){
        super("해당 휴대폰의 사용자가 존재하지 않습니다.");
    }
}
