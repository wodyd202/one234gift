package com.one234gift.userservice.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.userservice.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterSalesUserAPI_Test {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 영업사원_생성() throws Exception{
        RegisterUser registerUser = aRegisterUser().build();

        assertRegisterSalesUser(registerUser, status().isOk());
    }

    @Test
    void 잘못된_전화번호_입력() throws Exception {
        RegisterUser registerUser = aRegisterUser()
                .phone("invalid")
                .build();

        assertRegisterSalesUser(registerUser, status().isBadRequest());
    }

    @Test
    void 전화번호_미입력() throws Exception {
        RegisterUser registerUser = aRegisterUser()
                .phone(null)
                .build();

        assertRegisterSalesUser(registerUser, status().isBadRequest());
    }

    @Test
    void 잘못된_이름_입력() throws Exception {
        RegisterUser registerUser = aRegisterUser()
                .username("invalid")
                .build();

        assertRegisterSalesUser(registerUser, status().isBadRequest());
    }

    @Test
    void 이름_미입력() throws Exception {
        RegisterUser registerUser = aRegisterUser()
                .username(null)
                .build();

        assertRegisterSalesUser(registerUser, status().isBadRequest());
    }

    @Autowired UserRepository userRepository;

    @Test
    void 중복된_전화번호() throws Exception {
        RegisterUser registerUser = aRegisterUser()
                .phone("010-0001-0001")
                .build();

        userRepository.save(User.registerSalesUser(registerUser));

        assertRegisterSalesUser(registerUser, status().isBadRequest());
    }

    void assertRegisterSalesUser(RegisterUser registerUser, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(post("/api/sales-user")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType(APPLICATION_JSON))
                .andExpect(resultMatcher);
    }
}