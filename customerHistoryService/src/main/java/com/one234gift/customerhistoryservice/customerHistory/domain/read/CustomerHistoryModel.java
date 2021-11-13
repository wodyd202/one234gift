package com.one234gift.customerhistoryservice.domain.read;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerHistoryModel {
    private long customerId;
    private String who;
    private String payload;
    private String type;
    private LocalDateTime createDateTime;

    @Builder
    public CustomerHistoryModel(long customerId, String who, String payload, String type, LocalDateTime createDateTime) {
        this.customerId = customerId;
        this.who = who;
        this.payload = payload;
        this.type = type;
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "CustomerHistoryModel{" +
                "customerId='" + customerId + '\'' +
                ", who='" + who + '\'' +
                ", payload='" + payload + '\'' +
                ", type='" + type + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
