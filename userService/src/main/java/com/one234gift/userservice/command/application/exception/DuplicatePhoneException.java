package com.one234gift.userservice.command.application.exception;

public class DuplicatePhoneException extends RuntimeException {
    public DuplicatePhoneException(){
        super("해당 휴대폰의 중복된 사용자가 이미 존재합니다.");
    }

    public DuplicatePhoneException(String msg){
        super(msg);
    }
}
