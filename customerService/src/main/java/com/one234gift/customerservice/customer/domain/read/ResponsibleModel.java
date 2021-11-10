package com.one234gift.customerservice.customer.domain.read;

import lombok.Getter;

@Getter
public class ResponsibleModel {
    private String manager;

    public ResponsibleModel(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "ResponsibleModel{" +
                "manager='" + manager + '\'' +
                '}';
    }
}
