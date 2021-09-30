package com.one234gift.userservice.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestAccessToken {
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    public RequestAccessToken(String phone, String username) {
        this.username = username;
        this.phone = phone;
    }
}
