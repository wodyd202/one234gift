package com.one234gift.happycallservice.domain.read;

import lombok.Builder;

import java.time.LocalDate;

public class HappyCallModel {
    private long seq;
    private String userId;
    private long orderId;
    private LocalDate callDate;
    private boolean read;
    private String customerName, customerCategory;

    @Builder
    public HappyCallModel(long seq,
                          String userId,
                          long orderId,
                          String customerName,
                          String customerCategory,
                          LocalDate callDate,
                          boolean read) {
        this.seq = seq;
        this.userId = userId;
        this.orderId = orderId;
        this.callDate = callDate;
        this.read = read;
        this.customerCategory = customerCategory;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "HappyCallModel{" +
                "seq=" + seq +
                ", userId='" + userId + '\'' +
                ", orderId=" + orderId +
                ", callDate=" + callDate +
                ", read=" + read +
                ", customerName='" + customerName + '\'' +
                ", customerCategory='" + customerCategory + '\'' +
                '}';
    }
}
