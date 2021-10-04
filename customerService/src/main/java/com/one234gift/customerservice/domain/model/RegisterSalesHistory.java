package com.one234gift.customerservice.domain.model;

import com.one234gift.customerservice.domain.value.CustomerReactivity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterSalesHistory {
    private boolean sample;
    private boolean catalogue;
    private String content;
    private LocalDate callReservationDate;
    private CustomerReactivity reactivity;

    @Builder
    public RegisterSalesHistory(boolean sample, boolean catalogue, String content, LocalDate callReservationDate, CustomerReactivity reactivity) {
        this.sample = sample;
        this.catalogue = catalogue;
        this.content = content;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
    }
}
