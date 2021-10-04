package com.one234gift.orderservice.domain;

import com.one234gift.orderservice.domain.model.ChangeDelivery;
import com.one234gift.orderservice.domain.model.ChangeQuantity;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.CustomerInfoModel;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.read.SalesUserModel;
import com.one234gift.orderservice.domain.value.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.one234gift.orderservice.domain.OrderFixture.aCustomerInfo;
import static com.one234gift.orderservice.domain.OrderFixture.aSaleUser;
import static org.junit.Assert.*;

public class Order_Test {

    @Test
    void 상품명_입력(){
        Product product = new Product("상품명");
        assertEquals(product, new Product("상품명"));
        assertEquals(product.get(), "상품명");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid!@3",
            ""
    })
    void 상품명은_한글_영문_숫자_공백포함_조합_1자이상_20자이하만_허용(String product){
        assertThrows(IllegalArgumentException.class,()->{
            new Product(product);
        });
    }
    
    @Test
    void 영업자(){
        SalesUser salesUser = new SalesUser("userId","영업사원 이름");
        SalesUserModel salesUserModel = salesUser.toModel();
        assertEquals(salesUserModel.getPhone(), "userId");
        assertEquals(salesUserModel.getName(), "영업사원 이름");
    }

    @Test
    void 고객정보(){
        Long customerId = 1L;
        String name = "업체명";
        String category = "은행";
        CustomerInfo customerInfo = new CustomerInfo(customerId, name, category);
        CustomerInfoModel customerInfoModel = customerInfo.toModel();
        assertEquals(customerInfoModel.getCustomerId(), 1L);
        assertEquals(customerInfoModel.getName(),"업체명");
        assertEquals(customerInfoModel.getCategory(), "은행");
    }

    @Test
    void 배송지정보_입력() {
        Delivery delivery = new Delivery("배송지 상세 주소");
        assertEquals(delivery, new Delivery("배송지 상세 주소"));
        assertEquals(delivery.get(), "배송지 상세 주소");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid!@#",
            ""
    })
    void 배송지정보는_한글_숫자_영문_공백포함_조합으로_1자이상_50자_이하로입력해야함(String address){
        assertThrows(IllegalArgumentException.class,()->{
            new Delivery(address);
        });
    }

    @Test
    void 수량_입력(){
        OrderQuantity quantity = new OrderQuantity(3_0000);
        assertEquals(quantity.get(), 3_0000);
        assertEquals(quantity, new OrderQuantity(3_0000));
    }

    @Test
    void 잘못된_수량(){
        assertThrows(IllegalArgumentException.class,()->{
            new OrderQuantity(0);
        });
    }

    @Test
    void 매입단가_입력(){
        Price purchasePrice = new Price(123);
        assertEquals(purchasePrice, new Price(123));
        assertEquals(purchasePrice.get(), 123);
    }

    @Test
    void 잘못된_매입단가(){
        assertThrows(IllegalArgumentException.class,()->{
            new Price(0);
        });
    }

    @Test
    void 비고_입력(){
        Content content = new Content("비고 입력");
        assertEquals(content, new Content("비고 입력"));
        assertEquals(content.get(), "비고 입력");
    }

    @Test
    void 주문_생성(){
        RegisterOrder registerOrder = RegisterOrder.builder()
                .product("상품명")
                .customerId(1L)
                .delivery(ChangeDelivery.builder()
                        .addressDetail("배송지 주소")
                        .build())
                .quantity(ChangeQuantity.builder()
                        .quantity(30L)
                        .build())
                .purchasePrice(3000L)
                .salePrice(4000L)
                .content(null)
                .type(OrderType.SAMPLE)
                .build();
        SalesUser salesUser = aSaleUser()
                .phone("000-0000-0000")
                .name("영업자 이름")
                .build();
        CustomerInfo customerInfo = aCustomerInfo()
                .id(1L)
                .name("업체명")
                .category("은행")
                .build();
        Order order = Order.register(customerInfo, salesUser, registerOrder);

        OrderModel orderModel = order.toModel();
        assertEquals(orderModel.getProduct(), "상품명");
        assertEquals(orderModel.getCustomerInfo().getName(),"업체명");
        assertEquals(orderModel.getCustomerInfo().getCategory(), "은행");
        assertEquals(orderModel.getDelivery(), "배송지 주소");
        assertEquals(orderModel.getQuantity(), 30);
        assertEquals(orderModel.getPurchasePrice(), 3000);
        assertEquals(orderModel.getSalePrice(), 4000);
        assertNull(orderModel.getContent());
        assertEquals(orderModel.getType(), OrderType.SAMPLE);
    }

}
