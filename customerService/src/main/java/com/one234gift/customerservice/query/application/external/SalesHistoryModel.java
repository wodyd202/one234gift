package com.one234gift.customerservice.query.application.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SalesHistoryModel {
    private Long id;
    private Long customerId;
    private String content;
    private boolean sample, catalogue;
    private LocalDate callReservationDate;
    private String reactivity;
    private LocalDateTime createDateTime;
    private WriterModel manager;
}
