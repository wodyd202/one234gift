package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerModel {
    private String category;
    private BusinessInfoModel businessInfo;
    private PurchasingManagerModel purchasingManager;
    private ContactModel contact;
    private AddressModel address;
    private String note;
    private SaleState saleState;
    private AllowState allowState;

    @Builder
    public CustomerModel(String category,
                         BusinessInfoModel businessInfo,
                         PurchasingManagerModel purchasingManager,
                         ContactModel contact,
                         AddressModel address,
                         String note,
                         SaleState saleState,
                         AllowState allowState) {
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManager = purchasingManager;
        this.contact = contact;
        this.address = address;
        this.note = note;
        this.saleState = saleState;
        this.allowState = allowState;
    }
}
