package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChangedCallReservation extends AbstractCallReservationEvent {
    private LocalDate callReservationDate;

    public ChangedCallReservation(String salesHistoryId, LocalDate callReservationDate) {
        super(salesHistoryId);
        this.callReservationDate = callReservationDate;
    }
}
