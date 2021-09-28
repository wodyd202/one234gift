package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BusinessInfoModel {
    private String name;
    private String number;

    @Builder
    public BusinessInfoModel(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
