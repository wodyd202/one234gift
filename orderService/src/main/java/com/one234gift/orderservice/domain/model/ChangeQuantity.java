package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeQuantity {
    private Long quantity;

    @Builder
    public ChangeQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
