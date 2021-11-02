package com.one234gift.userservice.query.application.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("해당 아이디의 사용자가 존재하지 않습니다.");
    }
}
