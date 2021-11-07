package com.one234gift.happycallservice.domain.model;

import lombok.Setter;

@Setter
public class SalesUser {
    private String phone;

    protected SalesUser() {}

    public SalesUser(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
