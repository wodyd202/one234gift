package com.one234gift.orderservice.domain.value;

import com.one234gift.orderservice.domain.read.CustomerInfoModel;
import lombok.Builder;
import lombok.Setter;

import javax.persistence.Embedded;
import java.util.Objects;

@Setter
public class CustomerInfo {
    private long id;
    @Embedded
    private BusinessInfo businessInfo;
    private String category;

    protected CustomerInfo(){}

    @Builder
    public CustomerInfo(long id, String name, String category) {
        this.id = id;
        this.businessInfo = new BusinessInfo(name);
        this.category = category;
    }

    public CustomerInfoModel toModel() {
        return CustomerInfoModel.builder()
                .customerId(id)
                .category(category)
                .name(businessInfo.getName())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return id == that.id && Objects.equals(businessInfo, that.businessInfo) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessInfo, category);
    }
}
