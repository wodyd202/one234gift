package com.one234gift.saleshistoryservice.salesHistory.query.model;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SalesHistoryModels {
    private List<SalesHistoryModel> salesHistoryModels;
    private long totalElement;
    private long totalPage;
    private boolean prev;
    private boolean next;
    private Pageable pageable;

    @Builder
    public SalesHistoryModels(List<SalesHistoryModel> salesHistoryModels, long totalElement, Pageable pageable) {
        this.salesHistoryModels = salesHistoryModels;
        this.totalElement = totalElement;
        this.pageable = pageable;
        this.totalPage = (long) Math.ceil((double)totalElement / pageable.getSize());
        this.prev = !(totalPage == 0 || pageable.getPage() == 0);
        this.next = pageable.getPage() < totalPage - 1;
    }
}
