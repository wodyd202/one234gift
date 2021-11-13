package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.Getter;

@Getter
public class ChangedContentEvent extends AbstractSalesHistoryEvent{
    private String content;

    public ChangedContentEvent(String salesHistoryId, String content) {
        super(salesHistoryId);
        this.content = content;
    }
}
