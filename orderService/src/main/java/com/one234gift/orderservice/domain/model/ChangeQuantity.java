package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeQuantity {
    @Min(value = 1, message = "수량은 1개 이상 입력해주세요.")
    private long quantity;

    @Builder
    public ChangeQuantity(long quantity) {
        this.quantity = quantity;
    }
}
