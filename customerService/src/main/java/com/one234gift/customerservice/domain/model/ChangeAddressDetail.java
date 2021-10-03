package com.one234gift.customerservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeAddressDetail {
    @Pattern(message = "상세 주소는 [한글,숫자,영어](공백가능) 1자이상 20자이하로 입력해주세요.", regexp = "^[가-힣a-zA-Z0-9\\s]{1,20}$")
    private String detail;

    @Builder
    public ChangeAddressDetail(String detail) {
        this.detail = detail;
    }
}
