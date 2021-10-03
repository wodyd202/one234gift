package com.one234gift.customerservice.command.presentation;

import com.one234gift.customerservice.command.application.RegisterCustomerService;
import com.one234gift.customerservice.common.APIResponse;
import com.one234gift.customerservice.common.CommandException;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/customer")
public class RegisterCustomerAPI {
    @Autowired private RegisterCustomerService registerCustomerService;

    @PostMapping
    public APIResponse register(@Valid @RequestBody RegisterCustomer registerCustomer, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        CustomerModel customerModel = registerCustomerService.register(registerCustomer, principal.getName());
        return new APIResponse(customerModel, HttpStatus.OK);
    }
}
