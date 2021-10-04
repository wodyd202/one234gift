package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.customerservice.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SalesHistoryExceptionHandler {
    @ExceptionHandler(SalesHistoryNotFoundException.class)
    public APIResponse error(SalesHistoryNotFoundException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
