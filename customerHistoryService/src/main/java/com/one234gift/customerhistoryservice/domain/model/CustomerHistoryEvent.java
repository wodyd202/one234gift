package com.one234gift.customerhistoryservice.domain.model;

import lombok.Getter;

@Getter
public class CustomerHistoryEvent {
    private String customerId;
    private String manager;
    private String payload;
    private String type;

    @Override
    public String toString() {
        return "CustomerHistoryEvent{" +
                "customerId='" + customerId + '\'' +
                ", manager='" + manager + '\'' +
                ", payload='" + payload + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
