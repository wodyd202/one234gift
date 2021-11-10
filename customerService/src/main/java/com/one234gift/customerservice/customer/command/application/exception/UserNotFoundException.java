package com.one234gift.customerservice.customer.command.application.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("해당 사용자 정보가 존재하지 않습니다. 사용자 아이디를 확인해주세요.");
    }
}
