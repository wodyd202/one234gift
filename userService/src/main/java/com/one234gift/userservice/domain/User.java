package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.exception.AlreadyLeaveException;
import com.one234gift.userservice.domain.exception.AlreadyWorkingException;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Password;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.State;
import com.one234gift.userservice.domain.value.Username;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @AttributeOverride(name = "phone", column = @Column(name = "phone", length = 13, nullable = false))
    private final Phone phone;

    // 사용자 이름
    // 추후에 비밀번호로 사용될 예정
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "name", length = 10, nullable = false))
    private final Username name;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    private Password password;

    // 사용자 상태
    // NOT_APPROVED(퇴사)
    // APPROVED(재직)
    @Enumerated(STRING)
    @Column(nullable = false, length = 5)
    private State state;

    protected User(){
        phone = null;
        name = null;
    }

    protected User(Username username, Phone phone, Password password) {
        name = username;
        this.phone = phone;
        this.password = password;
    }

    /**
     * - registerUser to user
     * @param registerUser
     * @return
     */
    public static User registerSalesUser(RegisterUser registerUser) {
        return new SalesUser(new Username(registerUser.getUsername()),
                            new Phone(registerUser.getPhone()),
                            new Password(registerUser.getUsername()));
    }

    public static User registerAccountingUser(RegisterUser registerUser) {
        return new AccountingUser(new Username(registerUser.getUsername()),
                                new Phone(registerUser.getPhone()),
                                new Password(registerUser.getUsername()));
    }

    final public void register(RegisterUserValidator registerUserValidator, PasswordEncoder passwordEncoder){
        registerUserValidator.validation(phone);
        state = WORK;
        password = password.encode(passwordEncoder);
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

    public void comeBack() {
        verifyLeaved();
        state = WORK;
    }

    private void verifyLeaved() {
        if(state.equals(WORK)){
            throw new AlreadyWorkingException("이미 근무중인 사용자입니다.");
        }
    }

    public UserModel toModel(){
        return UserModel.builder()
                .username(name.get())
                .phone(phone.get())
                .state(state)
                .role(this.getClass().getSimpleName())
                .password(password.get())
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
