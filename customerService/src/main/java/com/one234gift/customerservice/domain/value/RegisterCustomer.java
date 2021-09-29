package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterCustomer {
    private String category;
    private ChangeBusinessInfo businessInfo;
    private ChangePurchasingManager purchasingManager;
    private ChangeContact contact;
    private ChangeAddress address;
    private String note;

    @Builder
    public RegisterCustomer(String category,
                            ChangeBusinessInfo businessInfo,
                            ChangePurchasingManager purchasingManager,
                            ChangeContact contact,
                            ChangeAddress address,
                            String note) {
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManager = purchasingManager;
        this.contact = contact;
        this.address = address;
        this.note = note;
    }
}
