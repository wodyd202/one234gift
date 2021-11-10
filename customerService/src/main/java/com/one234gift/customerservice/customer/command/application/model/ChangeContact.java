package com.one234gift.customerservice.customer.command.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeContact {
    @NotBlank(message = "대표 연락처를 입력해주세요.")
    @Pattern(message = "대표 연락처 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.", regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String mainTel;

    @Pattern(message = "기타 연락처 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.", regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String subTel;

    @Builder
    public ChangeContact(String mainTel, String subTel) {
        this.mainTel = mainTel;
        this.subTel = subTel;
    }
}
