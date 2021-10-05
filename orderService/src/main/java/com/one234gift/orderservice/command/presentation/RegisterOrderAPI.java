package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.command.application.RegisterOrderService;
import com.one234gift.orderservice.common.APIResponse;
import com.one234gift.orderservice.common.CommandException;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/order")
public class RegisterOrderAPI {
    @Autowired private RegisterOrderService registerOrderService;

    @PostMapping
    public APIResponse register(@Valid @RequestBody RegisterOrder registerOrder, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = registerOrderService.register(registerOrder);
        return new APIResponse(orderModel, HttpStatus.OK);
    }
}
