package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;

public class ChangedAddressDetailEvent extends ChangedCustomerEvent {

    public ChangedAddressDetailEvent(String addressDetail, long customerId, Manager manager) {
        super(customerId, manager, addressDetail);
    }

    @Override
    public String getType() {
        return "업체 상세주소 변경";
    }
}
