package com.one234gift.happycallservice.domain.value;

import com.one234gift.happycallservice.domain.model.SalesUser;

import javax.persistence.Embeddable;

@Embeddable
public class SalesUserInfo {
    private final String phone;

    protected SalesUserInfo() {
        phone = null;
    }

    public SalesUserInfo(String phone) {
        this.phone = phone;
    }

    public SalesUser toModel() {
        return new SalesUser(phone);
    }
}
