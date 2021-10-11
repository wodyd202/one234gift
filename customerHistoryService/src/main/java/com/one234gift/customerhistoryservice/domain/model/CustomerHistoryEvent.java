package com.one234gift.customerhistoryservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerHistoryEvent {
    private String customerId;
    private String manager;
    private String payload;
    private String type;

    @Builder
    public CustomerHistoryEvent(String customerId, String manager, String payload, String type) {
        this.customerId = customerId;
        this.manager = manager;
        this.payload = payload;
        this.type = type;
    }

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
