package com.one234gift.customerservice.customer.query.application.external;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 영업 기록 리스트 모델
 */
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
