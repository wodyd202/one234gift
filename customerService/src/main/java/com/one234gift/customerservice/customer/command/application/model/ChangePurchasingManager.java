package com.one234gift.customerservice.customer.command.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePurchasingManager {
    @NotBlank(message = "구매담당자 이름을 입력해주세요.")
    @Pattern(message = "구매담당자 이름은 한글조합 1자 이상 10자 이하로 입력해주세요.", regexp = "^[가-힣]{1,10}$")
    private String name;

    @Pattern(message = "이메일 형식이 올바르지 않습니다.", regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String email;

    @Pattern(message = "직위는 한글조합 1자이상 10자 이하로 입력해주세요.", regexp = "^[가-힣]{1,10}$")
    private String jobTitle;

    @Valid
    private ChangeContact contact;

    @Builder
    public ChangePurchasingManager(String name, String email, String jobTitle, ChangeContact contact) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.contact = contact;
    }
}
