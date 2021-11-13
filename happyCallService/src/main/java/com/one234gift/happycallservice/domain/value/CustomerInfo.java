package com.one234gift.happycallservice.domain.value;

import com.one234gift.happycallservice.domain.read.CustomerInfoModel;
import lombok.Builder;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
public class CustomerInfo {
    @Embedded
    private BusinessInfo businessInfo;
    private String category;

    protected CustomerInfo(){}

    @Builder
    public CustomerInfo(String name, String category) {
        this.businessInfo = new BusinessInfo(name);
        this.category = category;
    }

    public CustomerInfoModel toModel() {
        return CustomerInfoModel.builder()
                .category(category)
                .name(businessInfo.getName())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return Objects.equals(businessInfo, that.businessInfo) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessInfo, category);
    }
}
