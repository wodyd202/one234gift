package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.Getter;

@Getter
public class UpdatedCustomerEvent {
    private final CustomerModel customer;
    public UpdatedCustomerEvent(CustomerModel customerModel) {
        this.customer = customerModel;
    }
}
