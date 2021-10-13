package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.APITest;
import com.one234gift.orderservice.domain.model.ChangeDelivery;
import com.one234gift.orderservice.domain.model.RegisterOrder;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.one234gift.orderservice.domain.OrderFixture.aRegisterOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class RegisterOrderAPI_Test extends APITest {

    @Test
    void 주문_등록() throws Exception{
        RegisterOrder registerOrder = aRegisterOrder().build();
        assertRegisterOrder(registerOrder, status().isOk());
    }

    @Test
    void 상품명_미입력() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .product(null)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 상품명_빈값() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .product("")
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 고객_고유번호_미입력() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .customerId(null)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 배송지_정보_미입력() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .delivery(ChangeDelivery.builder()
                        .addressDetail(null)
                        .build())
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 배송지_정보_빈값() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .delivery(ChangeDelivery.builder()
                        .addressDetail("")
                        .build())
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 잘못된_수량() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .quantity(-1)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 잘못된_매입단가() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .purchasePrice(-1)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 잘못된_판매단가() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .salePrice(-1)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 주문_타입_미입력() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .type(null)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    private void assertRegisterOrder(RegisterOrder registerOrder, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(registerOrder)))
                .andExpect(resultMatcher);
    }
}