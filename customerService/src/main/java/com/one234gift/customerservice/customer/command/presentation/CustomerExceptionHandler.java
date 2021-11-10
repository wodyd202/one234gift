package com.one234gift.customerservice.customer.command.presentation;

import com.one234gift.customerservice.customer.domain.exception.CustomerNotFoundException;
import com.one234gift.customerservice.customer.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.customer.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.customer.domain.exception.AlreadySaleStopException;
import com.one234gift.customerservice.customer.domain.exception.PurchasingManagerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler({
            AlreadySaleStopException.class,
            AlreadySaleException.class,
            DataBaseNotAccessAbleException.class,
            /////////////////////////////////////////
            CustomerNotFoundException.class,
            PurchasingManagerNotFoundException.class
    })
    public ResponseEntity error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity error(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
