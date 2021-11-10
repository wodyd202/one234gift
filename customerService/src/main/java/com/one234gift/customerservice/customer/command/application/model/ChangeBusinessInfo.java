package com.one234gift.customerservice.customer.command.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeBusinessInfo {
    @NotBlank(message = "고객 업체명을 입력해주세요.")
    @Pattern(message = "고객 업체명은 한글 조합 3자 이상 30자 이하(공백 허용)로 입력해주세요.", regexp = "^[가-힣]{1}[가-힣\\s]{0,28}[가-힣]{0,1}$")
    private String name;

    @Pattern(message = "사업자번호는 [xxx-xx-xxxxx] 양식으로 입력해주세요.", regexp = "^(\\d{3,3})+[-]+(\\d{2,2})+[-]+(\\d{5,5})")
    private String number;

    @Builder
    public ChangeBusinessInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
