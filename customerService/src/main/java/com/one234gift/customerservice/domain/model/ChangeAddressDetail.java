package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeAddressDetail {
    private String detail;

    @Builder
    public ChangeAddressDetail(String detail) {
        this.detail = detail;
    }
}
