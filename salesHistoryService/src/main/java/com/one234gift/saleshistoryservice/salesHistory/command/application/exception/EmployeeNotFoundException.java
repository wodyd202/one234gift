package com.one234gift.saleshistoryservice.salesHistory.command.application.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(){
        super("해당 사용자 정보가 존재하지 않습니다.");
    }
}
