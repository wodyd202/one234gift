package com.one234gift.orderservice.domain.value;

import com.one234gift.orderservice.domain.read.SalesUserModel;
import lombok.Builder;

import java.util.Objects;

public class SalesUser {
    private final String phone;
    private final String name;

    @Builder
    public SalesUser(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public SalesUserModel toModel() {
        return SalesUserModel.builder()
                .name(name)
                .phone(phone)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesUser salesUser = (SalesUser) o;
        return Objects.equals(phone, salesUser.phone) && Objects.equals(name, salesUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, name);
    }
}
