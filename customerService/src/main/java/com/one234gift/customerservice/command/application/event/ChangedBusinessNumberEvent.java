package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;

public class ChangedBusinessNumberEvent extends ChangedCustomerEvent {
    public ChangedBusinessNumberEvent(String businessNumber, Long customerId, Manager manager) {
        super(customerId, manager, businessNumber);
    }

    @Override
    public String getType() {
        return "사업자번호 변경";
    }
}
