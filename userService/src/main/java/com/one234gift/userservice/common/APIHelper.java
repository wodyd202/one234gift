package com.one234gift.common.http;

import org.springframework.validation.Errors;

public class APIHelper {
    public static void verifyNotContainsError(Errors errors){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
    }
}
