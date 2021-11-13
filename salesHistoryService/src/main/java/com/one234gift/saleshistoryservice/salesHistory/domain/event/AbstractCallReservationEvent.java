package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.AllArgsConstructor;

abstract public class AbstractCallReservationEvent extends AbstractSalesHistoryEvent {
    public AbstractCallReservationEvent(String salesHistoryId) {
        super(salesHistoryId);
    }
}
