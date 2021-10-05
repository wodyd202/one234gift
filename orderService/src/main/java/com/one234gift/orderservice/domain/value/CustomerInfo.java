package com.one234gift.orderservice.domain.value;

import com.one234gift.orderservice.domain.read.CustomerInfoModel;
import lombok.Builder;

import java.util.Objects;

public class CustomerInfo {
    private long id;
    private String name;
    private String category;

    protected CustomerInfo(){}

    @Builder
    public CustomerInfo(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public CustomerInfoModel toModel() {
        return CustomerInfoModel.builder()
                .customerId(id)
                .category(category)
                .name(name)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }
}
