package com.one234gift.customerservice.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContectModel {
    private String mainTel,subTel;

    @Builder
    public ContectModel(String mainTel, String subTel) {
        this.mainTel = mainTel;
        this.subTel = subTel;
    }
}
