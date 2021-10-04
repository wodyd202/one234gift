package com.one234gift.orderservice.domain.model;

import com.one234gift.orderservice.domain.value.OrderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrder {
    private String product;
    private Long customerId;
    private ChangeDelivery delivery;
    private ChangeQuantity quantity;
    private Long purchasePrice;
    private Long salePrice;
    private String content;
    private OrderType type;

    @Builder
    public RegisterOrder(String product, Long customerId, ChangeDelivery delivery, ChangeQuantity quantity, Long purchasePrice, Long salePrice, String content, OrderType type) {
        this.product = product;
        this.customerId = customerId;
        this.delivery = delivery;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.content = content;
        this.type = type;
    }
}
