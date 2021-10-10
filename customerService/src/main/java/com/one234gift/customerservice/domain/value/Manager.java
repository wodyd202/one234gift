package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.read.ManagerModel;
import lombok.Builder;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class Manager implements Serializable {
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
