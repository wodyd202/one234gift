package com.one234gift.saleshistoryservice.salesHistory.command.presentation;

import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SalesHistoryExceptionHandler {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            EmployeeNotFoundException.class,
            SalesHistoryNotFoundException.class
    })
    public ResponseEntity<String> error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> error(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
