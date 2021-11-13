package com.one234gift.saleshistoryservice.salesHistory.domain.value;

import com.one234gift.saleshistoryservice.salesHistory.domain.read.WriterModel;
import lombok.Builder;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(phone, writer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
