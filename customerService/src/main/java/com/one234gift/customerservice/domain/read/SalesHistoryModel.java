package com.one234gift.customerservice.domain.read;

import com.one234gift.customerservice.domain.value.CustomerReactivity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SalesHistoryModel {
    private Long id;
    private Long customerId;
    private String content;
    private boolean sample, catalogue;
    private LocalDate callReservationDate;
    private CustomerReactivity reactivity;
    private LocalDateTime createDateTime;
    private ManagerModel manager;

    @Builder
    public SalesHistoryModel(Long id, Long customerId, String content, boolean sample, boolean catalogue, LocalDate callReservationDate, CustomerReactivity reactivity, LocalDateTime createDateTime, ManagerModel manager) {
        this.id = id;
        this.customerId = customerId;
        this.content = content;
        this.sample = sample;
        this.catalogue = catalogue;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
        this.createDateTime = createDateTime;
        this.manager = manager;
    }
}
