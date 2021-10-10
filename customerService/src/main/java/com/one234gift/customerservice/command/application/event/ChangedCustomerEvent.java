package com.one234gift.customerservice.command.application.event;

import com.one234gift.customerservice.domain.value.Manager;
import lombok.Getter;

import java.io.Serializable;

@Getter
abstract public class ChangedCustomerEvent implements Serializable {
    protected final long customerId;
    protected final String manager;
    protected final String payload;

    abstract public String getType();

    protected ChangedCustomerEvent(long customerId, Manager manager, String payload) {
        this.customerId = customerId;
        this.manager = manager.toModel().getName();
        if(payload == null){
            this.payload = "";
        }else{
            this.payload = payload;
        }
    }
}
