package com.one234gift.employeeService.employee.domain.exception;

public class AlreadyExistEmployeeException extends RuntimeException {
    public AlreadyExistEmployeeException(){
        super("해당 휴대폰의 중복된 사용자가 이미 존재합니다.");
    }

    public AlreadyExistEmployeeException(String msg){
        super(msg);
    }
}
