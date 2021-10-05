package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.command.application.exception.UserNotFoundException;
import com.one234gift.orderservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.command.application.exception.NotAbleRepositoryException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class RepositoryErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodName, Response response) {
        if(response.status() == 500){
            throw new NotAbleRepositoryException();
        }
        if(methodName.contains("findUser")){
            switch (response.status()){
                case 400:
                    throw new UserNotFoundException();
            }
        }
        if(methodName.contains("findById")){
            switch (response.status()){
                case 400:
                    throw new CustomerNotFoundException();
            }
        }
        return null;
    }
}
