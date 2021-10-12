package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemovePurchasingManager {
    private String name;
    private String mainTel;

    @Builder
    public RemovePurchasingManager(String name, String mainTel) {
        this.name = name;
        this.mainTel = mainTel;
    }
}
