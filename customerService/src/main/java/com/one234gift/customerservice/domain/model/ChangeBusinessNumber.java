package com.one234gift.customerservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeBusinessNumber {
    @Pattern(message = "사업자번호는 [xxx-xx-xxxxx] 양식으로 입력해주세요.", regexp = "^(\\d{3,3})+[-]+(\\d{2,2})+[-]+(\\d{5,5})")
    private String businessNumber;

    @Builder
    public ChangeBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}
