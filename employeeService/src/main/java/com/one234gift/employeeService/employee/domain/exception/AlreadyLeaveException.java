package com.one234gift.employeeService.employee.domain.exception;

public class AlreadyLeaveException extends RuntimeException {
    public AlreadyLeaveException(String msg) {
        super(msg);
    }
}
