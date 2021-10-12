package com.one234gift.orderservice.command.application.event;

import com.one234gift.orderservice.domain.read.CustomerInfoModel;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.read.SalesUserModel;
import com.one234gift.orderservice.domain.value.OrderState;
import com.one234gift.orderservice.domain.value.OrderType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderedEvent {
    private Long id;
    private String product;
    private CustomerInfoModel customerInfo;
    private SalesUserModel salesUser;
    private String content, delivery;
    private long quantity, purchasePrice, salePrice;
    private OrderType type;
    private LocalDateTime createDateTime;
    private OrderState state;

    public OrderedEvent(OrderModel orderModel) {
        this.id = orderModel.getId();
        this.product = orderModel.getProduct();
        this.customerInfo = orderModel.getCustomerInfo();
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
