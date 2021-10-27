package com.one234gift.customerservice.query.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerHistoryModel {
    private long customerId;
    private String type;
    private String payload;
    private String changer;
    private LocalDateTime createDateTime;

    public CustomerHistoryModel(long customerId, String type, String payload, String manager, LocalDateTime createDateTime) {
        this.customerId = customerId;
        this.type = type;
        this.payload = payload;
        this.changer = manager;
        this.createDateTime = createDateTime;
    }
}
