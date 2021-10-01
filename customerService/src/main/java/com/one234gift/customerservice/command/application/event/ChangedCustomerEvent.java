package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;
import lombok.Getter;

@Getter
abstract public class ChangedCustomerEvent {
    protected final long customerId;
    protected final Manager manager;
    protected final String payload;

    abstract public String getType();

    protected ChangedCustomerEvent(long customerId, Manager manager, String payload) {
        this.customerId = customerId;
        this.manager = manager;
        this.payload = payload;
    }
}
