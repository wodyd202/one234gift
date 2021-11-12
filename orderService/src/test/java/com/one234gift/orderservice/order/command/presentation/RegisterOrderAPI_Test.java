package com.one234gift.orderservice.order.command.presentation;

import com.one234gift.orderservice.OrderAPITest;
import com.one234gift.orderservice.order.command.application.StubUserRepository;
import com.one234gift.orderservice.order.command.application.model.RegisterOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.one234gift.orderservice.OrderFixture.aRegisterOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 주문 등록 API 테스트
 */
@WithMockUser(username = "000-0000-0000")
public class RegisterOrderAPI_Test extends OrderAPITest {
    @Autowired
    StubUserRepository stubUserRepository;

    @Override
    public void init() {
        stubUserRepository.save("000-0000-0000");
    }

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
                .delivery(null)
                .build();
        assertRegisterOrder(registerOrder, status().isBadRequest());
    }

    @Test
    void 배송지_정보_빈값() throws Exception {
        RegisterOrder registerOrder = aRegisterOrder()
                .delivery("")
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
