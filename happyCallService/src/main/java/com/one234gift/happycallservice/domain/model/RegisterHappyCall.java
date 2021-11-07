package com.one234gift.happycallservice.domain.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterHappyCall {
    private SalesUser writer;
    private long customerId;
    private LocalDate callReservationDate;

    @Builder
    public RegisterHappyCall(SalesUser writer, long customerId, LocalDate callReservationDate) {
        this.writer = writer;
        this.customerId = customerId;
        this.callReservationDate = callReservationDate;
    }
}
