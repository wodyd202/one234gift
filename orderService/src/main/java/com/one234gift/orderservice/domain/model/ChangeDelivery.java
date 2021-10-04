package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeDelivery {
    private String addressDetail;

    @Builder
    public ChangeDelivery(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
