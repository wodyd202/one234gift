package com.one234gift.customerservice.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeBusinessName {
    private String name;

    @Builder
    public ChangeBusinessName(String name) {
        this.name = name;
    }
}
