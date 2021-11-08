package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.command.application.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SalesHistoryExceptionHandler {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            UserNotFoundException.class,
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
