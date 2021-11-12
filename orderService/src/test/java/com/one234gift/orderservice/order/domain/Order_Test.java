package com.one234gift.orderservice.order.domain;

import com.one234gift.orderservice.order.command.application.OrderMapper;
import com.one234gift.orderservice.order.command.application.model.RegisterOrder;
import com.one234gift.orderservice.order.domain.read.CustomerInfoModel;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.domain.read.SalesUserModel;
import com.one234gift.orderservice.order.domain.value.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.one234gift.orderservice.OrderFixture.*;
import static com.one234gift.orderservice.order.domain.value.OrderState.CENCEL;
import static com.one234gift.orderservice.order.domain.value.OrderState.FINISH;
import static org.junit.Assert.*;

public class Order_Test {

    @Test
    void 상품명_입력(){
        // when
        Product product = new Product("상품명");

        // then
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
            // when
            new Product(product);
        });
    }
    
    @Test
    void 영업자(){
        // given
        SalesUser salesUser = new SalesUser("userId","영업사원 이름");

        // when
        SalesUserModel salesUserModel = salesUser.toModel();

        // then
        assertEquals(salesUserModel.getPhone(), "userId");
        assertEquals(salesUserModel.getName(), "영업사원 이름");
    }

    @Test
    void 고객정보(){
        // given
        CustomerInfo customerInfo = new CustomerInfo(1L, "업체명", "은행");

        // when
        CustomerInfoModel customerInfoModel = customerInfo.toModel();

        // then
        assertEquals(customerInfoModel.getCustomerId(), 1L);
        assertEquals(customerInfoModel.getName(),"업체명");
        assertEquals(customerInfoModel.getCategory(), "은행");
    }

    @Test
    void 배송지정보_입력() {
        // when
        Delivery delivery = new Delivery("배송지 상세 주소");

        // then
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
            // when
            new Delivery(address);
        });
    }

    @Test
    void 수량_입력(){
        // when
        OrderQuantity quantity = new OrderQuantity(3_0000);

        // then
        assertEquals(quantity.get(), 3_0000);
        assertEquals(quantity, new OrderQuantity(3_0000));
    }

    @Test
    void 잘못된_수량(){
        assertThrows(IllegalArgumentException.class,()->{
            // when
            new OrderQuantity(0);
        });
    }

    @Test
    void 매입단가_입력(){
        // when
        Price purchasePrice = new Price(123);

        // then
        assertEquals(purchasePrice, new Price(123));
        assertEquals(purchasePrice.get(), 123);
    }

    @Test
    void 잘못된_매입단가(){
        assertThrows(IllegalArgumentException.class,()->{
            // when
            new Price(0);
        });
    }

    @Test
    void 비고_입력(){
        // when
        Content content = new Content("비고 입력");

        // then
        assertEquals(content, new Content("비고 입력"));
        assertEquals(content.get(), "비고 입력");
    }

    @Test
    void 주문_생성(){
        // given
        RegisterOrder registerOrder = RegisterOrder.builder()
                .product("상품명")
                .customerId(1L)
                .delivery("배송지 주소")
                .quantity(30L)
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

        // when
        OrderMapper orderMapper = new OrderMapper();
        Order order = orderMapper.mapFrom(customerInfo, salesUser, registerOrder);
        OrderModel orderModel = order.toModel();

        // then
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

    @Test
    void 주문_취소(){
        // given
        Order order = aOrder(SalesUser.builder().build());

        // when
        order.cancel();

        // then
        assertEquals(order.toModel().getState(), CENCEL);
    }

    @Test
    void 해피콜_후(){
        // given
        Order order = aOrder(SalesUser.builder().build());

        // when
        order.finish();

        // then
        assertEquals(order.toModel().getState(), FINISH);
    }

}
