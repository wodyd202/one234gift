package com.one234gift.customerservice.customer.command.application.event;

import com.one234gift.customerservice.customer.domain.read.AddressModel;
import com.one234gift.customerservice.customer.domain.read.BusinessInfoModel;
import com.one234gift.customerservice.customer.domain.read.PurchasingManagerModel;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 고객 생성 이벤트
 */
@Getter
public class RegisteredCustomerEvent {
    private Long id;
    private String category;
    private BusinessInfoModel businessInfo;
    private String fax;
    private AddressModel address;
    private LocalDate createDate;
    private List<PurchasingManagerModel> purchasingManagers;

    public RegisteredCustomerEvent(Long id,
                                   String category,
                                   BusinessInfoModel businessInfo,
                                   List<PurchasingManagerModel> purchasingManagers,
                                   AddressModel address,
                                   String fax,
                                   LocalDate createDate) {
        this.id = id;
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.fax = fax;
        this.createDate = createDate;
    }
}
