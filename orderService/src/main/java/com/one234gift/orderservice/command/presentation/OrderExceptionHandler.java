package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.command.application.exception.SalesUserNotFoundException;
import com.one234gift.orderservice.command.application.exception.UserNotFoundException;
import com.one234gift.orderservice.domain.exception.AlreadyCenceledException;
import com.one234gift.orderservice.domain.exception.AlreadyDeliveryFinishedException;
import com.one234gift.orderservice.domain.exception.AlreadyOrderException;
import com.one234gift.orderservice.domain.exception.EnableOrderInfoChangeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            UserNotFoundException.class,
            SalesUserNotFoundException.class
    })
    public ResponseEntity<String> error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({
            AlreadyDeliveryFinishedException.class,
            AlreadyOrderException.class,
            EnableOrderInfoChangeException.class,
            AlreadyCenceledException.class
    })
    public ResponseEntity<String> error(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
