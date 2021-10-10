package com.one234gift.userservice.user.command.presentation;

import com.one234gift.userservice.APITest;
import com.one234gift.userservice.command.application.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserFixture.aAccountingUser;
import static com.one234gift.userservice.user.UserFixture.aSalesUser;
import static com.one234gift.userservice.user.UserTestHelper.persistUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class LeaveSalesUserAPI_Test extends APITest {
    @Autowired private UserRepository userRepository;

    @Test
    void 입력한_전화번호의_사용자가_경리담당자일_경우() throws Exception{
        persistUser(userRepository, aAccountingUser("000-0000-0001"));

        mockMvc.perform(delete("/api/sales-user/{userPk}", "000-0000-0001"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 영업_담당자_퇴사() throws Exception{
        persistUser(userRepository, aSalesUser("000-0000-0002"));

        mockMvc.perform(delete("/api/sales-user/{userPk}", "000-0000-0002"))
                .andExpect(status().isOk());
    }
}
