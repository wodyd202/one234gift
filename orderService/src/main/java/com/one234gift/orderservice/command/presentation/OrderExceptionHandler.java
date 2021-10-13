package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.command.application.exception.SalesUserNotFoundException;
import com.one234gift.orderservice.command.application.exception.UserNotFoundException;
import com.one234gift.orderservice.common.APIResponse;
import com.one234gift.orderservice.domain.exception.AlreadyCenceledException;
import com.one234gift.orderservice.domain.exception.AlreadyDeliveryFinishedException;
import com.one234gift.orderservice.domain.exception.AlreadyOrderException;
import com.one234gift.orderservice.domain.exception.EnableOrderInfoChangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            UserNotFoundException.class,
            SalesUserNotFoundException.class
    })
    public APIResponse error(RuntimeException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            AlreadyDeliveryFinishedException.class,
            AlreadyOrderException.class,
            EnableOrderInfoChangeException.class,
            AlreadyCenceledException.class
    })
    public APIResponse error(Exception e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
