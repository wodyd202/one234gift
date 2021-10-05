package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeDelivery {
    @NotBlank(message = "배송지 정보를 입력해주세요.")
    @Pattern(message = "배송지 정보는 [한글,숫자,영문](공백포함) 조합 1자 이상 50자 이하로 입력해주세요.", regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9\\s]{1,50}$")
    private String addressDetail;

    @Builder
    public ChangeDelivery(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
