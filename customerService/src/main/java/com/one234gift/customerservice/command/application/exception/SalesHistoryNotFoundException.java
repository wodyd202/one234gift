package com.one234gift.customerservice.command.application.exception;

public class SalesHistoryNotFoundException extends RuntimeException{
    public SalesHistoryNotFoundException(){
        super("해당 영업 기록이 존재하지 않습니다.");
    }
}
