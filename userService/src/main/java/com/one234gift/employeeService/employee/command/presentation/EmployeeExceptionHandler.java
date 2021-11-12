package com.one234gift.employeeService.employee.command.presentation;

import com.one234gift.employeeService.employee.domain.exception.AlreadyExistEmployeeException;
import com.one234gift.employeeService.employee.domain.exception.AlreadyLeaveException;
import com.one234gift.employeeService.employee.domain.exception.AlreadyWorkingException;
import com.one234gift.employeeService.employee.domain.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler({
            AlreadyExistEmployeeException.class,
            EmployeeNotFoundException.class,
            AlreadyWorkingException.class,
            AlreadyLeaveException.class
    })
    public ResponseEntity<String> error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
