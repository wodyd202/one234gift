package com.one234gift.customerhistoryservice.domain.read;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerHistoryModel {
    private String customerId;
    private String manager;
    private String payload;
    private String type;
    private LocalDateTime createDateTime;

    @Builder
    public CustomerHistoryModel(String customerId, String manager, String payload, String type, LocalDateTime createDateTime) {
        this.customerId = customerId;
        this.manager = manager;
        this.payload = payload;
        this.type = type;
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "CustomerHistoryModel{" +
                "customerId='" + customerId + '\'' +
                ", manager='" + manager + '\'' +
                ", payload='" + payload + '\'' +
                ", type='" + type + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
