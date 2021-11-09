package com.one234gift.userservice.domain.exception;

public class AlreadyExistUserException extends RuntimeException {
    public AlreadyExistUserException(){
        super("해당 휴대폰의 중복된 사용자가 이미 존재합니다.");
    }

    public AlreadyExistUserException(String msg){
        super(msg);
    }
}
