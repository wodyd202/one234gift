package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.read.ManagerModel;
import lombok.Builder;

public class Manager {
    private final String name;
    private final String phone;

    protected Manager(){name = null; phone = null;}

    @Builder
    public Manager(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public ManagerModel toModel(){
        return ManagerModel.builder()
                .name(name)
                .phone(phone)
                .build();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
