package com.one234gift.customerservice.domain.value;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeBusinessInfo {
    private String name;
    private String number;

    @Builder
    public ChangeBusinessInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
