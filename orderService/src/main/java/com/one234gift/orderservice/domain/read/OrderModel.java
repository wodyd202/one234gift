package com.one234gift.orderservice.domain.read;

import com.one234gift.orderservice.domain.value.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderModel {
    private Long id;
    private String product;
    private CustomerInfoModel customerInfo;
    private SalesUserModel salesUser;
    private String content, delivery;
    private long quantity, purchasePrice, salePrice;
    private OrderType type;
    private LocalDateTime createDateTime;
    private OrderState state;

    public OrderModel(Long id,
                      Product product,
                      CustomerInfo customerInfo,
                      OrderQuantity orderQuantity,
                      Price salePrice,
                      OrderState state,
                      LocalDateTime createDateTime){
        this.id = id;
        this.product = product.get();
        this.customerInfo = customerInfo.toModel();
        this.quantity = orderQuantity.get();
        this.salePrice = salePrice.get();
        this.state = state;
        this.createDateTime = createDateTime;
    }

    @Builder
    public OrderModel(Long id,
                      Product product,
                      CustomerInfo customerInfo,
                      SalesUser salesUser,
                      Content content,
                      Delivery delivery,
                      OrderQuantity quantity,
                      Price purchasePrice,
                      Price salePrice,
                      OrderType type,
                      LocalDateTime createDateTime,
                      OrderState state) {
        this.id = id;
        this.product = product.get();
        this.customerInfo = customerInfo.toModel();
        this.salesUser = salesUser.toModel();
        this.content = content == null ? null : content.get();
        this.delivery = delivery.get();
        this.quantity = quantity.get();
        this.purchasePrice = purchasePrice.get();
        this.salePrice = salePrice.get();
        this.type = type;
        this.createDateTime = createDateTime;
        this.state = state;
    }
}
