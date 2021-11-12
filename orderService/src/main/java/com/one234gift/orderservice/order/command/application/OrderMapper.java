package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.model.RegisterOrder;
import com.one234gift.orderservice.order.domain.Order;
import com.one234gift.orderservice.order.domain.value.*;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapFrom(CustomerInfo customerInfo, SalesUser salesUser, RegisterOrder registerOrder) {
        return Order.builder()
                .product(new Product(registerOrder.getProduct()))
                .customerInfo(customerInfo)
                .salesUser(salesUser)
                .delivery(new Delivery(registerOrder.getDelivery()))
                .content(registerOrder.getContent() == null ? null : new Content(registerOrder.getContent()))
                .quantity(new OrderQuantity(registerOrder.getQuantity()))
                .purchasePrice(new Price(registerOrder.getPurchasePrice()))
                .salePrice(new Price(registerOrder.getSalePrice()))
                .type(registerOrder.getType())
                .build();
    }
}
