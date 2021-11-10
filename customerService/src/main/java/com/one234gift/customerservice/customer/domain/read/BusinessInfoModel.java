package com.one234gift.customerservice.customer.domain.read;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfoModel {
    private String name;
    private String number;

    @Builder
    public BusinessInfoModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    protected void changeBusinessNumber(String businessNumber) {
        this.number = businessNumber;
    }

    protected void changeBusinessName(String businessName) {
        this.name = businessName;
    }

    @Override
    public String toString() {
        return "BusinessInfoModel{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
