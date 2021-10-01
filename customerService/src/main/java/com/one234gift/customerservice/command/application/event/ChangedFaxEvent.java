package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;

public class ChangedFaxEvent extends ChangedCustomerEvent {
    public ChangedFaxEvent(String fax, Long customerId, Manager manager) {
        super(customerId, manager, fax);
    }

    @Override
    public String getType() {
        return "팩스번호 변경";
    }
}
