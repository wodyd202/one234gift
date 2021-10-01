package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeContact {
    private String mainTel;
    private String subTel;

    @Builder
    public ChangeContact(String mainTel, String subTel) {
        this.mainTel = mainTel;
        this.subTel = subTel;
    }
}
