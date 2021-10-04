package com.one234gift.orderservice.domain;

import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;

public class OrderFixture {

    public static SalesUser.SalesUserBuilder aSaleUser() {
        return SalesUser.builder()
                .name("영업자명")
                .phone("000-0000-0000");
    }

    public static CustomerInfo.CustomerInfoBuilder aCustomerInfo() {
        return CustomerInfo.builder()
                .id(1L)
                .category("은행")
                .name("업체명");
    }
}
