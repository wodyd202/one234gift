package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.command.application.ChangeOrderStateService;
import com.one234gift.orderservice.common.APIResponse;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/order/{orderId}")
public class ChangeOrderStateAPI {
    @Autowired
    private ChangeOrderStateService changeOrderStateService;

    @DeleteMapping
    public APIResponse cencel(@PathVariable Long orderId, Principal principal){
        OrderModel orderModel = changeOrderStateService.cencel(orderId, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }

    @PutMapping
    public APIResponse finish(@PathVariable Long orderId, Principal principal){
        OrderModel orderModel = changeOrderStateService.finish(orderId, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }
}
