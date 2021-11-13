package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.Getter;

@Getter
public class RemovedCallReservationEvent extends AbstractCallReservationEvent {
    public RemovedCallReservationEvent(String salesHistoryId) {
        super(salesHistoryId);
    }
}
