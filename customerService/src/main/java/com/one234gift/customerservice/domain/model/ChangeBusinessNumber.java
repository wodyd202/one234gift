package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeBusinessNumber {
    private String businessNumber;

    @Builder
    public ChangeBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}
