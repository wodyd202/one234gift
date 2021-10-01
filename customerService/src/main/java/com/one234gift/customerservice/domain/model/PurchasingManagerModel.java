package com.one234gift.customerservice.domain.model;

import com.one234gift.customerservice.domain.read.ContectModel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchasingManagerModel {
    private String name;
    private String email;
    private String jobTitle;
    private ContectModel contact;

    @Builder
    public PurchasingManagerModel(String name, String email, String jobTitle, ContectModel contact) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.contact = contact;
    }
}
