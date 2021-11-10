package com.one234gift.customerservice.customer.command.application.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RemovePurchasingManager {
    @NotNull(message = "구매담당자의 고유 번호를 입력해주세요.")
    private Long id;

    @Builder
    public RemovePurchasingManager(Long id) {
        this.id = id;
    }
}
