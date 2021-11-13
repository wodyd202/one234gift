package com.one234gift.saleshistoryservice.salesHistory.command.application.exception;

public class SalesHistoryNotFoundException extends RuntimeException {
    public SalesHistoryNotFoundException(){
        super("영업 기록이 존재하지 않습니다.");
    }
}
