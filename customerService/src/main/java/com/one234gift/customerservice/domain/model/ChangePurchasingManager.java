package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangePurchasingManager {
    private String name;
    private String email;
    private String jobTitle;
    private ChangeContact contact;

    @Builder
    public ChangePurchasingManager(String name, String email, String jobTitle, ChangeContact contact) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.contact = contact;
    }
}
