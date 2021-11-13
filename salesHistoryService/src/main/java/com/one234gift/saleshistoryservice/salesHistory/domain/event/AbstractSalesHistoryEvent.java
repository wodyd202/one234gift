package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract public class AbstractSalesHistoryEvent {
    private String salesHistoryId;
}
