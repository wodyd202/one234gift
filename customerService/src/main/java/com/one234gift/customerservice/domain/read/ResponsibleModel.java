package com.one234gift.customerservice.domain.read;

import lombok.Getter;

@Getter
public class ResponsibleModel {
    private String manager;

    public ResponsibleModel(String manager) {
        this.manager = manager;
    }
}
