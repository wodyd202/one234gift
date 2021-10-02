package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.read.ManagerModel;
import lombok.Builder;
import lombok.Setter;

@Setter
public class Manager {
    private String username;
    private String phone;

    protected Manager(){}

    @Builder
    public Manager(String name, String phone) {
        this.username = name;
        this.phone = phone;
    }

    public ManagerModel toModel(){
        return ManagerModel.builder()
                .name(username)
                .phone(phone)
                .build();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
