package com.one234gift.customerservice.domain.read;

import com.one234gift.customerservice.domain.value.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CustomerModel {
    private Long id;
    private String category;
    private BusinessInfoModel businessInfo;
    private List<PurchasingManagerModel> purchasingManagers;
    private AddressModel address;
    private SaleState saleState;
    private String fax;
    private LocalDateTime createDateTime;

    @Builder
    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         PurchasingManagers purchasingManagers,
                         Address address,
                         SaleState saleState,
                         Tel fax,
                         LocalDateTime createDateTime
                         ){
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.purchasingManagers = purchasingManagers.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.fax = fax.get();
        this.createDateTime = createDateTime;
    }

    @Builder
    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         Address address,
                         SaleState saleState,
                         Tel fax,
                         LocalDateTime createDateTime) {
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.fax = fax.get();
        this.createDateTime = createDateTime;
    }
}
