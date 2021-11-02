package com.one234gift.customerservice.domain.read;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasingManagerModel {
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private ContectModel contact;

    @Builder
    public PurchasingManagerModel(Long id, String name, String email, String jobTitle, ContectModel contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PurchasingManagerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", contact=" + contact +
                '}';
    }
}
