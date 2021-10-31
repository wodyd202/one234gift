package com.one234gift.happycallservice.domain.value;

import lombok.Setter;

@Setter
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
