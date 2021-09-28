package com.one234gift.userservice.user.domain;

import com.one234gift.userservice.domain.AccountingUser;
import com.one234gift.userservice.domain.SalesUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.exception.AlreadyWorkingException;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.State;
import com.one234gift.userservice.domain.value.Username;
import com.one234gift.userservice.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.one234gift.userservice.domain.value.State.LEAVE;
import static com.one234gift.userservice.user.UserFixture.aSalesUser;
import static org.junit.jupiter.api.Assertions.*;

public class User_Test {

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "",
            "홍길동123",
            "홍길 동"
    })
    void 사용자_이름은_한글만_허용(String username){
        assertThrows(IllegalArgumentException.class,()->{
            new Username(username);
        });
    }

    @Test
    void 사용자_이름_입력(){
        Username username = new Username("홍길동");
        assertEquals(username, new Username("홍길동"));
        assertEquals(username.get(), "홍길동");
    }

    @Test
    void 휴대폰_입력(){
        Phone phone = new Phone("010-0000-0000");

        assertEquals(phone, new Phone("010-0000-0000"));
        assertEquals(phone.get(), "010-0000-0000");
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void 휴대폰_양식이_올바르지_않음(){
        new Phone("01000000000");
    }

    @Test
    void 영업_담당자_생성(){
        RegisterUser registerUser = RegisterUser.builder()
                .phone("010-0000-0000")
                .username("이름")
                .build();
        User user = User.registerSalesUser(registerUser);
        UserModel userModel = user.toModel();
        assertTrue(user instanceof SalesUser);
        assertEquals(userModel.getPhone(),"010-0000-0000");
        assertEquals(userModel.getUsername(),"이름");
    }

    @Test
    void 경리_담당자_생성(){
        RegisterUser registerUser = RegisterUser.builder()
                .phone("010-0000-0000")
                .username("이름")
                .build();
        User user = User.registerAccountingUser(registerUser);
        UserModel userModel = user.toModel();
        assertTrue(user instanceof AccountingUser);
        assertEquals(userModel.getPhone(),"010-0000-0000");
        assertEquals(userModel.getUsername(),"이름");
    }

    @Test
    void 퇴사(){
        User salesUser = aSalesUser("010-0000-0000");
        salesUser.leave();
        UserModel userModel = salesUser.toModel();
        assertEquals(userModel.getState(), LEAVE);
    }

    @Test
    void 이미_퇴사한_사용자(){
        User salesUser = aSalesUser("010-0000-0000");
        salesUser.leave();
        assertThrows(AlreadyLeaveException.class, ()->{
            salesUser.leave();
        });
    }

    // 퇴사 후 다시 복귀한 경우
    @Test
    void 재영입(){
        User salesUser = aSalesUser("000-0000-0000");
        salesUser.leave();
        salesUser.comeBack();
        UserModel userModel = salesUser.toModel();
        assertEquals(userModel.getState(), State.WORK);
    }

    @Test
    void 이미_근무중인_사용자(){
        User salesUser = aSalesUser("000-0000-0000");
        salesUser.leave();
        salesUser.comeBack();
        assertThrows(AlreadyWorkingException.class, ()->{
            salesUser.comeBack();
        });
    }
    
}
