package com.one234gift.calllistservice.call.presentation;

import com.one234gift.calllistservice.call.domain.exception.ReservationCallNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationCallExceptionHandler {
    @ExceptionHandler(ReservationCallNotFoundException.class)
    public ResponseEntity<String> error(ReservationCallNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> error(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
