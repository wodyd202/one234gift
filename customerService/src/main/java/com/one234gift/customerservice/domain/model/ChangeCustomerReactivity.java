package com.one234gift.customerservice.domain.model;

import com.one234gift.customerservice.domain.value.CustomerReactivity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCustomerReactivity {
    @NotNull(message = "반응도를 입력해주세요.")
    private CustomerReactivity reactivity;

    @Builder
    public ChangeCustomerReactivity(CustomerReactivity reactivity) {
        this.reactivity = reactivity;
    }
}
