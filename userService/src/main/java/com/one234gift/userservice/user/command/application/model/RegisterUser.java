package com.one234gift.userservice.command.application.model;

import com.one234gift.userservice.domain.value.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    @NotBlank(message = "사용자 전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.")
    private String phone;

    @NotBlank(message = "사용자의 이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{1,10}$", message = "사용자의 이름은 한글 조합 1자 이상 10자 이하로 입력해주세요.")
    private String username;

    @NotNull(message = "사용자의 권한을 입력해주세요.")
    private UserRole role;
}
