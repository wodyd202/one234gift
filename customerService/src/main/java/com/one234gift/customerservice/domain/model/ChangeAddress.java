package com.one234gift.customerservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeAddress {
    @NotBlank(message = "지역을 입력해주세요.")
    @Pattern(message = "지역은 [한글,숫자,영어](공백가능) 1자이상 15자 이하로 입력해주세요.", regexp = "^[가-힣0-9a-zA-Z\\s]{1,15}$")
    private String location;

    @Pattern(message = "상세 주소는 [한글,숫자,영어](공백가능) 1자이상 20자이하로 입력해주세요.", regexp = "^[가-힣a-zA-Z0-9\\s]{1,20}$")
    private String addressDetail;

    @Builder
    public ChangeAddress(String location, String addressDetail) {
        this.location = location;
        this.addressDetail = addressDetail;
    }
}
