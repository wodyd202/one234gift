package com.one234gift.saleshistoryservice.domain.value;

import com.one234gift.saleshistoryservice.domain.read.WriterModel;
import lombok.Builder;

import javax.persistence.Embeddable;

@Embeddable
public class Writer {
    private String name;
    private String phone;

    protected Writer(){}

    @Builder
    public Writer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public WriterModel toModel(){
        return WriterModel.builder()
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
