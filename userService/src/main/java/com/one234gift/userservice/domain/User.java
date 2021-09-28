package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.State;
import com.one234gift.userservice.domain.value.Username;

import javax.persistence.*;

import static com.one234gift.userservice.domain.value.State.LEAVE;
import static com.one234gift.userservice.domain.value.State.WORK;
import static javax.persistence.EnumType.STRING;

/**
 * 사용자
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
abstract public class User {
    // 사용자 전화번호
    // 추후에 아이디로 사용될 예정
    // 유니크 해야함
    @EmbeddedId
    private final Phone phone;

    // 사용자 이름
    // 추후에 비밀번호로 사용될 예정
    @Embedded
    private final Username name;

    // 사용자 상태
    // NOT_APPROVED(퇴사)
    // APPROVED(재직)
    @Enumerated(STRING)
    private State state;

    protected User(Username username, Phone phone) {
        name = username;
        this.phone = phone;
    }

    /**
     * - registerUser to user
     * @param registerUser
     * @return
     */
    public static User registerSalesUser(RegisterUser registerUser) {
        return new SalesUser(new Username(registerUser.getUsername()),new Phone(registerUser.getPhone()));
    }

    public static User registerAccountingUser(RegisterUser registerUser) {
        return new AccountingUser(new Username(registerUser.getUsername()),new Phone(registerUser.getPhone()));
    }

    final public void register(RegisterUserValidator registerUserValidator){
        registerUserValidator.validation(phone);
        state = WORK;
    }

    final public void leave(){
        verifyWorking();
        state = LEAVE;
    }

    private void verifyWorking() {
        if(state.equals(LEAVE)){
            throw new AlreadyLeaveException("이미 퇴사한 사용자입니다.");
        }
    }

    public UserModel toModel(){
        return UserModel.builder()
                .username(name.get())
                .phone(phone.get())
                .state(state)
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone.get() +
                ", name=" + name.get() +
                ", state=" + state +
                '}';
    }
}
