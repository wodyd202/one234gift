package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.Getter;

@Getter
public class RegisteredCustomerEvent {
    private final CustomerModel customer;
    public RegisteredCustomerEvent(CustomerModel customer) {
        this.customer = customer;
    }
}
