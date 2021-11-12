package com.one234gift.orderservice.order.command.presentation;

import com.one234gift.orderservice.order.command.application.RegisterOrderService;
import com.one234gift.orderservice.common.CommandException;
import com.one234gift.orderservice.order.command.application.model.RegisterOrder;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 주문 등록 API
 */
@RestController
@RequestMapping("api/order")
public class RegisterOrderAPI {
    @Autowired private RegisterOrderService registerOrderService;

    @PostMapping
    public ResponseEntity<OrderModel> register(@Valid @RequestBody RegisterOrder registerOrder, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = registerOrderService.register(registerOrder);
        return ResponseEntity.ok(orderModel);
    }
}
