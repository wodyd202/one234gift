package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePurchasePrice {
    private long price;

    @Builder
    public ChangePurchasePrice(long price) {
        this.price = price;
    }
}
