package com.one234gift.employeeService.employee.domain.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(){
        super("해당 아이디의 사용자가 존재하지 않습니다.");
    }
}
