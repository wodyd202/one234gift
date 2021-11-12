package com.one234gift.orderservice.order.command.presentation;

import com.one234gift.orderservice.order.command.application.ChangeOrderStateService;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/order/{orderId}")
public class ChangeOrderStateAPI {
    @Autowired
    private ChangeOrderStateService changeOrderStateService;

    @DeleteMapping
    public ResponseEntity<OrderModel> cencel(@PathVariable Long orderId, Principal principal){
        OrderModel orderModel = changeOrderStateService.cencel(orderId, principal.getName());
        return ResponseEntity.ok(orderModel);
    }

    @PutMapping
    public ResponseEntity<OrderModel> finish(@PathVariable Long orderId, Principal principal){
        OrderModel orderModel = changeOrderStateService.finish(orderId, principal.getName());
        return ResponseEntity.ok(orderModel);
    }
}
