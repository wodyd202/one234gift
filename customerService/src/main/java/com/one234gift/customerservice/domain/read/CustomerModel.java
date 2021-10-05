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
    private ManagerModel manager;
    private String fax;
    private LocalDateTime createDateTime;

    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         Address address,
                         SaleState saleState,
                         Manager manager,
                         Tel fax,
                         LocalDateTime createDateTime
                         ){
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.manager = manager.toModel();
        this.fax = fax.get();
        this.createDateTime = createDateTime;
    }

    @Builder
    public CustomerModel(Long id,
                         String category,
                         BusinessInfoModel businessInfo,
                         List<PurchasingManagerModel> purchasingManagers,
                         AddressModel address,
                         SaleState saleState,
                         ManagerModel manager,
                         String fax,
                         LocalDateTime createDateTime) {
        this.id = id;
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.saleState = saleState;
        this.manager = manager;
        this.fax = fax;
        this.createDateTime = createDateTime;
    }
}
