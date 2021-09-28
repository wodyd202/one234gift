package com.one234gift.userservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class RegisterUser {
    @NotBlank(message = "사용자 전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.")
    private String phone;

    @NotBlank(message = "사용자의 이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{1,10}$", message = "사용자의 이름은 한글 조합 1자 이상 10자 이하로 입력해주세요.")
    private String username;

    @Builder
    public RegisterUser(String phone, String username) {
        this.phone = phone;
        this.username = username;
    }
}
