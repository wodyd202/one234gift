package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangePurchasingManager {
    private String name;
    private String email;
    private String jobTitle;

    @Builder
    public ChangePurchasingManager(String name, String email, String jobTitle) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
    }
}
