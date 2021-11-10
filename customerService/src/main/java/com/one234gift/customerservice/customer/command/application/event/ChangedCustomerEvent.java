package com.one234gift.customerservice.customer.command.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
abstract public class ChangedCustomerEvent implements Serializable {
    protected Long id;
    protected String who;
}
