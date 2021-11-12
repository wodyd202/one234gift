package com.one234gift.saleshistoryservice.domain.value;

import com.one234gift.saleshistoryservice.domain.read.WriterModel;
import lombok.Builder;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Embeddable
public class Writer {
    private String username;
    private String phone;

    protected Writer(){}

    @Builder
    public Writer(String name, String phone) {
        this.username = name;
        this.phone = phone;
    }

    public WriterModel toModel(){
        return WriterModel.builder()
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
