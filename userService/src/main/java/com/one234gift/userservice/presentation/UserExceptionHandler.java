package com.one234gift.userservice.presentation;

import com.one234gift.userservice.application.exception.DuplicatePhoneException;
import com.one234gift.userservice.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(DuplicatePhoneException.class)
    public APIResponse error(DuplicatePhoneException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
