package com.one234gift.customerservice.customer.command.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ChangeBusinessName {
    @NotBlank(message = "고객 업체명을 입력해주세요.")
    @Pattern(message = "고객 업체명은 한글 조합 3자 이상 30자 이하(공백 허용)로 입력해주세요.", regexp = "^[가-힣]{1}[가-힣\\s]{0,28}[가-힣]{0,1}$")
    private String name;

    @Builder
    public ChangeBusinessName(String name) {
        this.name = name;
    }
}
