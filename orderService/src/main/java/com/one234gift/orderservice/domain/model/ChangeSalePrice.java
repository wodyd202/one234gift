package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeSalePrice {
    private long price;

    @Builder
    public ChangeSalePrice(long price) {
        this.price = price;
    }
}
