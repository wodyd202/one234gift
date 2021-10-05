package com.one234gift.orderservice.domain.value;

import com.one234gift.orderservice.domain.read.SalesUserModel;
import lombok.Builder;
import lombok.Setter;

import java.util.Objects;

@Setter
public class SalesUser {
    private String phone;
    private String username;

    protected SalesUser(){}

    @Builder
    public SalesUser(String phone, String name) {
        this.phone = phone;
        this.username = name;
    }

    public SalesUserModel toModel() {
        return SalesUserModel.builder()
                .name(username)
                .phone(phone)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesUser salesUser = (SalesUser) o;
        return Objects.equals(phone, salesUser.phone) && Objects.equals(username, salesUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, username);
    }
}
