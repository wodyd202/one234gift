package com.one234gift.customerservice.common;

import org.springframework.validation.Errors;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CommandException extends RuntimeException{
    private final Errors errors;

    public CommandException(Errors errors) {
        this.errors = errors;
    }

    public List<String> getErrorMessages(){
        return errors.getAllErrors().stream()
                .map(c->c.getDefaultMessage())
                .collect(toList());
    }
}
