package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.Getter;

@Getter
public class ChangedSampleEvent extends AbstractSalesHistoryEvent{
    private boolean sample;

    public ChangedSampleEvent(String salesHistoryId, boolean sample) {
        super(salesHistoryId);
        this.sample = sample;
    }
}
