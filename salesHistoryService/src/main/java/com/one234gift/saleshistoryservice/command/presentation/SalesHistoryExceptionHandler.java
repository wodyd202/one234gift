package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.command.application.exception.UserNotFoundException;
import com.one234gift.saleshistoryservice.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SalesHistoryExceptionHandler {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            UserNotFoundException.class,
            SalesHistoryNotFoundException.class
    })
    public APIResponse error(RuntimeException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public APIResponse error(IllegalArgumentException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
