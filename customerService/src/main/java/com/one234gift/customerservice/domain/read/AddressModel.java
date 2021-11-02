package com.one234gift.customerservice.domain.read;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressModel {
    private String location;
    private String addressDetail;

    @Builder
    public AddressModel(String location, String addressDetail) {
        this.location = location;
        this.addressDetail = addressDetail;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "location='" + location + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}
