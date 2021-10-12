package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class RemovePurchasingManager {
    @NotBlank(message = "구매담당자 이름을 입력해주세요.")
    @Pattern(message = "구매담당자 이름은 한글조합 1자 이상 10자 이하로 입력해주세요.", regexp = "^[가-힣]{1,10}$")
    private String name;

    @NotBlank(message = "대표 연락처를 입력해주세요.")
    @Pattern(message = "대표 연락처 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.", regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String mainTel;

    @Builder
    public RemovePurchasingManager(String name, String mainTel) {
        this.name = name;
        this.mainTel = mainTel;
    }
}
