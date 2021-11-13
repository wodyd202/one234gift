package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 예약콜 이벤트
 */
@Getter
public class CallReservedEvent extends AbstractCallReservationEvent {
    private Customer customer;
    private String reserver;
    private LocalDate callReservationDate;

    @Builder
    public CallReservedEvent(String reserver,
                             String salesHistoryId,
                             Customer customer,
                             LocalDate callReservationDate) {
        super(salesHistoryId);
        this.reserver = reserver;
        this.customer = customer;
        this.callReservationDate = callReservationDate;
    }
}
