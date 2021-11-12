package com.one234gift.userservice.command.presentation;

import com.one234gift.userservice.domain.exception.AlreadyExistUserException;
import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.exception.AlreadyWorkingException;
import com.one234gift.userservice.domain.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({
            AlreadyExistUserException.class,
            UserNotFoundException.class,
            AlreadyWorkingException.class,
            AlreadyLeaveException.class
    })
    public ResponseEntity<String> error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
