package com.one234gift.customerservice.query.application.external;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SalesHistoryModels {
    private List<SalesHistoryModel> salesHistoryModels;
    private long totalElement;

    @Builder
    public SalesHistoryModels(List<SalesHistoryModel> salesHistoryModels, long totalElement) {
        this.salesHistoryModels = salesHistoryModels;
        this.totalElement = totalElement;
    }
}