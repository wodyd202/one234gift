package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeAddress {
    private String location;
    private String addressDetail;

    @Builder
    public ChangeAddress(String location, String addressDetail) {
        this.location = location;
        this.addressDetail = addressDetail;
    }
}
