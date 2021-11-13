package com.one234gift.saleshistoryservice.salesHistory.command.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCallReservationDate {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate callReservationDate;

    @Builder
    public ChangeCallReservationDate(LocalDate callReservationDate) {
        this.callReservationDate = callReservationDate;
    }
}
