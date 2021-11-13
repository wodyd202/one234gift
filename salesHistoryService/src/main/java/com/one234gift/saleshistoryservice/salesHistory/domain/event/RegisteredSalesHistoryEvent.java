package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerReactivity;
import lombok.Builder;

import java.time.LocalDate;

public class RegisteredSalesHistoryEvent extends AbstractSalesHistoryEvent{
    private long customerId;
    private String historyContent;
    private boolean sample, catalogue;
    private LocalDate callReservationDate;
    private CustomerReactivity reactivity;
    private LocalDate createDate;

    @Builder
    public RegisteredSalesHistoryEvent(String salesHistoryId,
                                       long customerId,
                                       String historyContent,
                                       boolean sample,
                                       boolean catalogue,
                                       LocalDate callReservationDate,
                                       CustomerReactivity reactivity,
                                       LocalDate createDate) {
        super(salesHistoryId);
        this.customerId = customerId;
        this.historyContent = historyContent;
        this.sample = sample;
        this.catalogue = catalogue;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
        this.createDate = createDate;
    }
}
