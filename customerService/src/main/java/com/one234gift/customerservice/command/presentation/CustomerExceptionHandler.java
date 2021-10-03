package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public APIResponse error(CustomerNotFoundException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
