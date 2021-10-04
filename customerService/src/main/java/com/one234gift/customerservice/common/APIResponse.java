package com.one234gift.customerservice.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class APIResponse extends ResponseEntity<HashMap<String, Object>> {
    public APIResponse(HttpStatus status){
        super(createBody(null, status), status);
    }

    public APIResponse(Object body, HttpStatus status) {
        super(createBody(body, status), status);
    }

    private static HashMap<String, Object> createBody(Object body, HttpStatus status){
        HashMap<String,Object> result = new HashMap<>();
        result.put("success", status.is3xxRedirection() || status.is2xxSuccessful());
        if(body != null){
            result.put("response", body);
        }
        return result;
    }
}