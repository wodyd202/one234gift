package com.one234gift.customerservice.customer.command.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ChangeFax {
    @Pattern(message = "팩스 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.", regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String fax;

    @Builder
    public ChangeFax(String fax) {
        this.fax = fax;
    }
}
