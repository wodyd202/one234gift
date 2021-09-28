package com.one234gift.userservice.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.userservice.domain.model.RegisterUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterAccountingUserAPI_Test extends APITest{
    @Test
    void 경리_담당자_생성() throws Exception{
        RegisterUser registerUser = aRegisterUser().build();

        mockMvc.perform(post("/api/accounting-user")
                .content(objectMapper.writeValueAsString(registerUser))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
