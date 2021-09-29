package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchasingManagerModel {
    private String name;
    private String email;
    private String jobTitle;

    @Builder
    public PurchasingManagerModel(String name, String email, String jobTitle) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
    }
}
