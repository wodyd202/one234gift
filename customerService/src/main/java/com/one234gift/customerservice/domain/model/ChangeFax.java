package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeFax {
    private String fax;

    @Builder
    public ChangeFax(String fax) {
        this.fax = fax;
    }
}
