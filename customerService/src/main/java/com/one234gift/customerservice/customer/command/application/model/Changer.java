package com.one234gift.customerservice.customer.command.application.model;

import lombok.Getter;

@Getter
public class Changer {
    private String id;

    protected Changer(){}

    public Changer(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }
}
