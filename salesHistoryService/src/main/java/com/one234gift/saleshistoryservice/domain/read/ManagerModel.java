package com.one234gift.saleshistoryservice.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerModel {
    private String name, phone;

    @Builder
    public ManagerModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
