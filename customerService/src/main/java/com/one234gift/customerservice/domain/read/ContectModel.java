package com.one234gift.customerservice.domain.read;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContectModel {
    private String mainTel,subTel;

    @Builder
    public ContectModel(String mainTel, String subTel) {
        this.mainTel = mainTel;
        this.subTel = subTel;
    }
}
