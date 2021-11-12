package com.one234gift.orderservice.order.domain.value;

import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Embeddable
public class BusinessInfo {
    private String name;

    protected BusinessInfo(){}

    public BusinessInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
