package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.domain.exception.AlreadySaleStopException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler({
            AlreadySaleStopException.class,
            AlreadySaleException.class
    })
    public ResponseEntity error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity error(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity error(CustomerNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
