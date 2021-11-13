package com.one234gift.calllistservice.call.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TargetCustomerModel {
    private long customerId;
    private String category;
    private String name;

    @Builder
    public TargetCustomerModel(long customerId, String category, String name) {
        this.customerId = customerId;
        this.category = category;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TargetCustomerModel{" +
                "customerId=" + customerId +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
