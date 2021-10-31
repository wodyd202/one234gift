package com.one234gift.happycallservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterHappyCall {
    private SalesUser salesUser;
    private long customerId;
    private LocalDate when;

    @Builder
    public RegisterHappyCall(SalesUser salesUser, long customerId, LocalDate when) {
        this.salesUser = salesUser;
        this.customerId = customerId;
        this.when = when;
    }
}
