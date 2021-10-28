package com.one234gift.orderservice.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SalesUserModel {
    private String phone,name;

    @Builder
    public SalesUserModel(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SalesUserModel{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
