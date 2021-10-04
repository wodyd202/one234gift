package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.common.APIResponse;
import com.one234gift.customerservice.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.domain.exception.AlreadySaleStopException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler({
            AlreadySaleStopException.class,
            AlreadySaleException.class
    })
    public APIResponse error(RuntimeException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public APIResponse error(IllegalArgumentException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public APIResponse error(CustomerNotFoundException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
