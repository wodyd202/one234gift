package com.one234gift.userservice.user.command.presentation;

import com.one234gift.userservice.APITest;
import com.one234gift.userservice.domain.model.RegisterUser;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "000-0000-0000")
public class RegisterAccountingUserAPI_Test extends APITest {

    @Test
    void 경리_담당자_생성() throws Exception{
        RegisterUser registerUser = aRegisterUser().build();

        mockMvc.perform(post("/api/accounting-user")
                .content(objectMapper.writeValueAsString(registerUser))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
