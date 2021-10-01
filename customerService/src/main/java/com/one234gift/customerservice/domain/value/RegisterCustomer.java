package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.model.ChangeAddress;
import com.one234gift.customerservice.domain.model.ChangeBusinessInfo;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterCustomer {
    private String category;
    private ChangeBusinessInfo businessInfo;
    private List<ChangePurchasingManager> purchasingManagers;
    private ChangeAddress address;
    private String fax;

    @Builder
    public RegisterCustomer(String category,
                            ChangeBusinessInfo businessInfo,
                            List<ChangePurchasingManager> purchasingManagers,
                            ChangeAddress address,
                            String fax) {
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.fax = fax;
    }
}
