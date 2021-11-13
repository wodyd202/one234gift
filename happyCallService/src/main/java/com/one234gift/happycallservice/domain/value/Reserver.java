package com.one234gift.happycallservice.domain.value;

import com.one234gift.happycallservice.domain.model.SalesUser;

import javax.persistence.Embeddable;

@Embeddable
public class Reserver {
    private final String phone;

    protected Reserver() {
        phone = null;
    }

    public Reserver(String phone) {
        this.phone = phone;
    }

    public SalesUser toModel() {
        return new SalesUser(phone);
    }
}
