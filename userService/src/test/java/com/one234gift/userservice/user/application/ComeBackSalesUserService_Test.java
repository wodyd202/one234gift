package com.one234gift.userservice.user.application;

import com.one234gift.userservice.command.application.ComeBackSalesUserService;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.State;
import com.one234gift.userservice.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;
import static com.one234gift.userservice.user.UserTestHelper.persistUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComeBackSalesUserService_Test {
    @Autowired
    ComeBackSalesUserService comeBackSalesUserService;
    @Autowired UserRepository userRepository;

    @Test
    void 재영입() {
        User salesUser = UserFixture.aSalesUser("010-0000-0000");
        salesUser.leave();
        persistUser(userRepository, salesUser);
        comeBackSalesUserService.comeback(new Phone("010-0000-0000"));

        UserModel user = findByPhone(userRepository, new Phone("010-0000-0000")).toModel();
        assertEquals(user.getState(), State.WORK);
    }
}
