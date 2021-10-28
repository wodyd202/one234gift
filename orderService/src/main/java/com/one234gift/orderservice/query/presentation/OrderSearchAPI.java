package com.one234gift.orderservice.query.presentation;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.query.application.OrderSearchService;
import com.one234gift.orderservice.query.model.OrderModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/order")
public class OrderSearchAPI {
    @Autowired
    private OrderSearchService orderSearchService;

    @GetMapping
    public ResponseEntity<OrderModels> findAll(Pageable pageable, Principal principal){
        OrderModels orderModels = orderSearchService.findAll(principal.getName(), pageable);
        return ResponseEntity.ok(orderModels);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderModel> findById(@PathVariable long orderId, Principal principal){
        OrderModel orderModel = orderSearchService.findById(orderId);
        return ResponseEntity.ok(orderModel);
    }
}
