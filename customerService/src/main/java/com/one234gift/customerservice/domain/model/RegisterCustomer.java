package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class RegisterCustomer {
    @NotBlank(message = "분류를 입력해주세요.")
    @Pattern(message = "분류는 한글조합 1자 이상 15자 이하만 허용합니다.", regexp = "^[가-힣]{1,15}$")
    private String category;

    @Valid
    private ChangeBusinessInfo businessInfo;

    @Valid
    @Size(min = 1, message = "고객 담당자를 입력해주세요.")
    private List<ChangePurchasingManager> purchasingManagers;

    @Valid
    private ChangeAddress address;

    @Pattern(message = "팩스 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.", regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String fax;

    @Builder
    public RegisterCustomer(String category,
                            ChangeBusinessInfo businessInfo,
                            List<ChangePurchasingManager> purchasingManagers,
                            ChangeAddress address,
                            String fax) {
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.fax = fax;
    }
}
