package com.one234gift.happycallservice.domain.value;

import javax.persistence.Embeddable;

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
