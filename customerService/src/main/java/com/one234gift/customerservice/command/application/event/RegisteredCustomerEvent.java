package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.read.CustomerModel;

public class RegistereCustomerEvent {
    private final CustomerModel customer;
    public RegistereCustomerEvent(CustomerModel customer) {
        this.customer = customer;
    }
}
