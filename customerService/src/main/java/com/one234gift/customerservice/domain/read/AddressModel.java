package com.one234gift.customerservice.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressModel {
    private String location;
    private String addressDetail;

    @Builder
    public AddressModel(String location, String addressDetail) {
        this.location = location;
        this.addressDetail = addressDetail;
    }
}
