package com.one234gift.customerhistoryservice.application.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 고객 이력 이벤트
 */
@Getter
public class CustomerHistoryEvent {
    private long customerId;
    private String who;
    private String payload;
    private String type;

    @Builder
    public CustomerHistoryEvent(long customerId, String who, String payload, String type) {
        this.customerId = customerId;
        this.who = who;
        this.payload = payload;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CustomerHistoryEvent{" +
                "customerId='" + customerId + '\'' +
                ", who='" + who + '\'' +
                ", payload='" + payload + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
