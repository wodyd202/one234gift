package com.one234gift.userservice.user.presentation;

import com.one234gift.userservice.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.userservice.user.UserTestHelper.persistUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComeBackSalesUserAPI_Test extends APITest {
    @Autowired UserRepository userRepository;

    @Test
    void 재영입() throws Exception {
        User salesUser = UserFixture.aSalesUser("000-0000-0000");
        salesUser.leave();
        persistUser(userRepository, salesUser);

        mockMvc.perform(put("/api/sales-user/{userPk}/comeback","000-0000-0000"))
                .andExpect(status().isOk());
    }
}
