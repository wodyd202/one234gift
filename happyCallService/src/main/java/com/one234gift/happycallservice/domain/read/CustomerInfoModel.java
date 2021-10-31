package com.one234gift.happycallservice.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerInfoModel {
    private long customerId;
    private String name, category;

    @Builder
    public CustomerInfoModel(long customerId, String name, String category) {
        this.customerId = customerId;
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return "CustomerInfoModel{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
