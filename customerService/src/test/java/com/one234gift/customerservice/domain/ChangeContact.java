package com.one234gift.customerservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeContact {
    private String mainTel;
    private String subTel;
    private String fax;

    @Builder
    public ChangeContact(String mainTel, String subTel, String fax) {
        this.mainTel = mainTel;
        this.subTel = subTel;
        this.fax = fax;
    }
}
