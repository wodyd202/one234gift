package com.one234gift.customerservice.domain.read;

import com.one234gift.customerservice.domain.value.Manager;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerHistoryModel {
    private long customerId;
    private String type;
    private String payload;
    private ManagerModel manager;
    private LocalDateTime createDateTime;

    public CustomerHistoryModel(long customerId, String type, String payload, Manager manager, LocalDateTime createDateTime) {
        this.customerId = customerId;
        this.type = type;
        this.payload = payload;
        this.manager = manager.toModel();
        this.createDateTime = createDateTime;
    }
}
