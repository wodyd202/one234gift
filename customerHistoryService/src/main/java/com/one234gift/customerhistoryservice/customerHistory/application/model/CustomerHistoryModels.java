package com.one234gift.customerhistoryservice.application.model;

import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerHistoryModels {
    private List<CustomerHistoryModel> customerHistoryModels;
    private long totalElement;

    @Builder
    public CustomerHistoryModels(List<CustomerHistoryModel> customerHistoryModels, long totalElement) {
        this.customerHistoryModels = customerHistoryModels;
        this.totalElement = totalElement;
    }
}
