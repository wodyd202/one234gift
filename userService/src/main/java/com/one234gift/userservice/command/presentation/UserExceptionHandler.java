package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.command.application.exception.DuplicatePhoneException;
import com.one234gift.userservice.command.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({
            DuplicatePhoneException.class,
            PhoneNotFoundException.class
    })
    public APIResponse error(RuntimeException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
