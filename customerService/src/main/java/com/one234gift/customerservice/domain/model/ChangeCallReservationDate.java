package com.one234gift.customerservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCallReservationDate {
    private LocalDate callReservationDate;

    @Builder
    public ChangeCallReservationDate(LocalDate callReservationDate) {
        this.callReservationDate = callReservationDate;
    }
}
