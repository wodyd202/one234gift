package com.one234gift.happycallservice.presentation;

import com.one234gift.happycallservice.domain.exception.HappyCallNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HappyCallExceptionHandler {

    @ExceptionHandler(HappyCallNotFoundException.class)
    public ResponseEntity<String> error(HappyCallNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
