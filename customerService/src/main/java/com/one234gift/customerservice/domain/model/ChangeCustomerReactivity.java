package com.one234gift.customerservice.domain.model;

import com.one234gift.customerservice.domain.value.CustomerReactivity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeCustomerReactivity {
    private CustomerReactivity reactivity;

    @Builder
    public ChangeCustomerReactivity(CustomerReactivity reactivity) {
        this.reactivity = reactivity;
    }
}
