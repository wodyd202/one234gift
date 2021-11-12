package com.one234gift.employeeService.employee.domain.exception;

public class AlreadyWorkingException extends RuntimeException {
    public AlreadyWorkingException(String msg) {
        super(msg);
    }
}
