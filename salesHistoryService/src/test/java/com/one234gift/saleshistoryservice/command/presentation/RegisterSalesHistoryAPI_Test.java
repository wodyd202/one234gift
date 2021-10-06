package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.APITest;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;

import static com.one234gift.saleshistoryservice.domain.SalesHistoryFixture.aRegisterSalesHistory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class RegisterSalesHistoryAPI_Test extends APITest {

    @Test
    void 영업기록_생성() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().build();
        assertRegisterSalesHistory(salesHistory, status().isOk());
    }

    @Test
    void 영업내용_미입력() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().content(null).build();
        assertRegisterSalesHistory(salesHistory, status().isBadRequest());
    }

    @Test
    void 영업내용_빈값() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().content("").build();
        assertRegisterSalesHistory(salesHistory, status().isBadRequest());
    }

    @Test
    void 영업내용은_특수문자를_허용하지_않음() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().content("!@#").build();
        assertRegisterSalesHistory(salesHistory, status().isBadRequest());
    }

    @Test
    void 예약콜은_필수항목이_아님() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().callReservationDate(null).build();
        assertRegisterSalesHistory(salesHistory, status().isOk());
    }

    @Test
    void 예약콜_입력시_미래의_날짜로_입력해야함() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().callReservationDate(LocalDate.now()).build();
        assertRegisterSalesHistory(salesHistory, status().isBadRequest());
    }

    @Test
    void 반응도_미입력() throws Exception {
        RegisterSalesHistory salesHistory = aRegisterSalesHistory().reactivity(null).build();
        assertRegisterSalesHistory(salesHistory, status().isBadRequest());
    }

    void assertRegisterSalesHistory(RegisterSalesHistory registerSalesHistory, ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(post("/api/sales-history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(registerSalesHistory)))
                .andExpect(resultMatcher);
    }

}
