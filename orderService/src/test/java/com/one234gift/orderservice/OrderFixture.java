package com.one234gift.orderservice;

import com.one234gift.orderservice.domain.Order;
import com.one234gift.orderservice.domain.model.ChangeDelivery;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.OrderType;
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

    public static RegisterOrder.RegisterOrderBuilder aRegisterOrder() {
        return RegisterOrder.builder()
                .product("상품명")
                .customerId(1L)
                .delivery(ChangeDelivery.builder()
                        .addressDetail("배송지 주소")
                        .build())
                .quantity(30L)
                .purchasePrice(3000L)
                .salePrice(4000L)
                .content(null)
                .type(OrderType.SAMPLE);
    }

    public static SalesUser aSalesUser(String userId){
        return SalesUser.builder()
                .phone(userId)
                .name(userId)
                .build();
    }

    public static Order aOrder(SalesUser salesUser){
        RegisterOrder registerOrder = OrderFixture.aRegisterOrder().build();
        Order order = Order.register(aCustomerInfo().build(), salesUser, registerOrder);
        order.place();
        return order;
    }
}
