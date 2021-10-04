package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;
import com.one234gift.customerservice.domain.value.SaleState;

public class ChangedStateEvent extends ChangedCustomerEvent {
    public ChangedStateEvent(SaleState saleState, Long id, Manager manager) {
        super(id, manager,saleState.toString());
    }

    @Override
    public String getType() {
        return "고객 상태 변경";
    }
}
