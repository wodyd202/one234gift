package com.one234gift.userservice.user.domain;

import com.one234gift.userservice.command.application.UserMapper;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.exception.AlreadyWorkingException;
import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.UserState;
import com.one234gift.userservice.domain.value.Username;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.one234gift.userservice.domain.value.UserState.LEAVE;
import static com.one234gift.userservice.user.UserFixture.aUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * 사용자 도메인 테스트
 */
public class User_Test {

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "",
            "홍길동123",
            "홍길 동"
    })
    void 사용자_이름은_한글만_허용(String username){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Username(username);
        });
    }

    @Test
    void 사용자_이름_입력(){
        // when
        Username username = new Username("홍길동");

        // then
        assertEquals(username, new Username("홍길동"));
        assertEquals(username.get(), "홍길동");
    }

    @Test
    void 휴대폰_입력(){
        // when
        Phone phone = new Phone("010-0000-0000");

        // then
        assertEquals(phone, new Phone("010-0000-0000"));
        assertEquals(phone.get(), "010-0000-0000");
    }

    @Test
    public void 휴대폰_양식이_올바르지_않음(){
        // when
        assertThrows(IllegalArgumentException.class,()->{
            new Phone("01000000000");
        });
    }

    @Test
    void 사용자_생성(){
        // given
        UserMapper userMapper = new UserMapper();
        RegisterUser registerUser = RegisterUser.builder()
                .phone("010-0000-0000")
                .username("이름")
                .role(UserRole.SALES_USER)
                .build();

        // when
        User user = userMapper.mapFrom(registerUser, mock(PasswordEncoder.class));
        UserModel userModel = user.toModel();

        //then
        assertEquals(userModel.getPhone(), "010-0000-0000");
        assertEquals(userModel.getUsername(), "이름");
        assertEquals(userModel.getState(), UserState.WORK);
        assertEquals(userModel.getRole(), UserRole.SALES_USER);
    }

    @Test
    void 퇴사(){
        // given
        User salesUser = aUser("010-0000-0000");

        // when
        salesUser.leave();
        UserModel userModel = salesUser.toModel();

        // then
        assertEquals(userModel.getState(), LEAVE);
    }

    @Test
    void 이미_퇴사한_사용자(){
        // given
        User salesUser = aUser("010-0000-0000");
        salesUser.leave();

        // when
        assertThrows(AlreadyLeaveException.class, ()->{
            salesUser.leave();
        });
    }

    // 퇴사 후 다시 복귀한 경우
    @Test
    void 재영입(){
        // given
        User salesUser = aUser("000-0000-0000");
        salesUser.leave();

        // when
        salesUser.comeBack();
        UserModel userModel = salesUser.toModel();

        // then
        assertEquals(userModel.getState(), UserState.WORK);
    }

    @Test
    void 이미_근무중인_사용자(){
        // given
        User salesUser = aUser("000-0000-0000");
        salesUser.leave();
        salesUser.comeBack();

        // when
        assertThrows(AlreadyWorkingException.class, ()->{
            salesUser.comeBack();
        });
    }
    
}
