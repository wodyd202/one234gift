package com.one234gift.happycallservice.domain.model;

import lombok.Getter;

@Getter
public class OrderEvent {
    private long id;
    private SalesUser salesUser;
    private CustomerInfo customerInfo;

    public String getUserId() {
        return salesUser.phone;
    }
    public String getCustomerName(){ return customerInfo.name; }
    public String getCustomerCategory(){ return customerInfo.category; }

    @Getter
    public class CustomerInfo {
        private String name, category;
    }

    @Getter
    public class SalesUser {
        private String phone;
    }
}
