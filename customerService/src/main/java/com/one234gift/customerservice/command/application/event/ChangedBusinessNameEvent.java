package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;
import lombok.Builder;

public class ChangedBusinessNameEvent extends ChangedCustomerEvent{

    @Builder
    public ChangedBusinessNameEvent(String businessName, long customerId, Manager manager) {
        super(customerId, manager, businessName);
    }

    @Override
    public String getType() {
        return "업체명 수정";
    }

}
