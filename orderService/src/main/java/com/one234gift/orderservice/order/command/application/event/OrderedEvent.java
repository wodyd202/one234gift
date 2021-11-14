package com.one234gift.orderservice.order.domain.event;

import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.domain.read.SalesUserModel;
import com.one234gift.orderservice.order.domain.value.OrderState;
import com.one234gift.orderservice.order.domain.value.OrderType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderedEvent {
    private Long id;
    private String product;
    private long customerId;
    private SalesUserModel salesUser;
    private String content, delivery;
    private long quantity, purchasePrice, salePrice;
    private OrderType type;
    private LocalDateTime createDateTime;
    private OrderState state;

    public OrderedEvent(OrderModel orderModel) {
        this.id = orderModel.getId();
        this.product = orderModel.getProduct();
        customerId = orderModel.getCustomerInfo().getCustomerId();
        this.salesUser = orderModel.getSalesUser();
        this.content = orderModel.getContent();
        this.delivery = orderModel.getDelivery();
        this.quantity = orderModel.getQuantity();
        this.purchasePrice = orderModel.getPurchasePrice();
        this.salePrice = orderModel.getSalePrice();
        this.type = orderModel.getType();
        this.createDateTime = orderModel.getCreateDateTime();
        this.state = orderModel.getState();
    }
}
