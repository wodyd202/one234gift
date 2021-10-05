package com.one234gift.orderservice.domain.read;

import com.one234gift.orderservice.domain.value.OrderState;
import com.one234gift.orderservice.domain.value.OrderType;
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

    @Builder
    public OrderModel(Long id,
                      String product,
                      CustomerInfoModel customerInfo,
                      SalesUserModel salesUser,
                      String content,
                      String delivery,
                      long quantity,
                      long purchasePrice,
                      long salePrice,
                      OrderType type,
                      LocalDateTime createDateTime,
                      OrderState state) {
        this.id = id;
        this.product = product;
        this.customerInfo = customerInfo;
        this.salesUser = salesUser;
        this.content = content;
        this.delivery = delivery;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.type = type;
        this.createDateTime = createDateTime;
        this.state = state;
    }
}
