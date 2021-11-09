package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.event.ComebackedUserEvent;
import com.one234gift.userservice.domain.event.LeavedUserEvent;
import com.one234gift.userservice.domain.event.RegisteredUserEvent;
import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.exception.AlreadyWorkingException;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static com.one234gift.userservice.domain.value.UserState.LEAVE;
import static com.one234gift.userservice.domain.value.UserState.WORK;
import static javax.persistence.EnumType.STRING;

/**
 * 사용자
 */
@Entity
@Table(name = "users")
@DynamicUpdate
final public class User extends AbstractAggregateRoot<User> {
    // 사용자 전화번호
    // 추후에 아이디로 사용될 예정
    // 유니크 해야함
    @Getter
    @EmbeddedId
    @AttributeOverride(name = "phone", column = @Column(name = "phone", length = 13, nullable = false))
    private final Phone phone;

    // 사용자 이름
    // 추후에 비밀번호로 사용될 예정
    @Embedded
    @AttributeOverride(name = "username", column = @Column(name = "name", length = 10, nullable = false))
    private final Username name;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    private Password password;

    // 사용자 상태
    // NOT_APPROVED(퇴사)
    // APPROVED(재직)
    @Enumerated(STRING)
    @Column(nullable = false, length = 5)
    private UserState state;

    // 사용자 권한
    @Enumerated(STRING)
    @Column(nullable = false, length = 15)
    private final UserRole role;

    protected User(){
        phone = null;
        name = null;
        role = null;
    }

    @Builder
    public User(Phone phone, Username name, UserRole role, PasswordEncoder passwordEncoder) {
        this.phone = phone;
        this.name = name;
        this.password = new Password(passwordEncoder.encode(name.get()));
        this.role = role;
        this.state = WORK;

        // 사용자 생성 이벤트
        registerEvent(new RegisteredUserEvent(phone, name, password, role));
    }

    /**
     * # 퇴사
     */
    public void leave(){
        verifyWorking();
        state = LEAVE;

        // 퇴사 이벤트
        registerEvent(new LeavedUserEvent(phone));
    }

    private void verifyWorking() {
        if(state.equals(LEAVE)){
            throw new AlreadyLeaveException("이미 퇴사한 사용자입니다.");
        }
    }

    /**
     * # 재입사
     */
    public void comeBack() {
        verifyLeaved();
        state = WORK;

        // 재입사 이벤트
        registerEvent(new ComebackedUserEvent(phone));
    }

    private void verifyLeaved() {
        if(state.equals(WORK)){
            throw new AlreadyWorkingException("이미 근무중인 사용자입니다.");
        }
    }

    /**
     * 조회 모델로 변환
     */
    public UserModel toModel(){
        return UserModel.builder()
                .username(name.get())
                .phone(phone.get())
                .state(state)
                .role(role)
                .password(password.get())
                .build();
    }
}
