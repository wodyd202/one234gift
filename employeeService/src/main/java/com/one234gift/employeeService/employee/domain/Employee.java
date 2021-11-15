package com.one234gift.employeeService.employee.domain;

import com.one234gift.employeeService.employee.domain.exception.AlreadyLeaveException;
import com.one234gift.employeeService.employee.domain.exception.AlreadyWorkingException;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.domain.value.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static com.one234gift.employeeService.employee.domain.value.EmployeeState.LEAVE;
import static com.one234gift.employeeService.employee.domain.value.EmployeeState.WORK;
import static javax.persistence.EnumType.STRING;

/**
 * 사용자
 */
@Entity
@Table(name = "employee")
@DynamicUpdate
final public class Employee {
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
    @AttributeOverride(name = "name", column = @Column(name = "name", length = 10, nullable = false))
    private final EmployeeName name;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    private Password password;

    // 사용자 상태
    // NOT_APPROVED(퇴사)
    // APPROVED(재직)
    @Enumerated(STRING)
    @Column(nullable = false, length = 5)
    private EmployeeState state;

    // 사용자 권한
    @Enumerated(STRING)
    @Column(nullable = false, length = 19)
    private final EmployeePosition role;

    protected Employee(){
        phone = null;
        name = null;
        role = null;
    }

    @Builder
    public Employee(Phone phone, EmployeeName name, EmployeePosition role, PasswordEncoder passwordEncoder) {
        this.phone = phone;
        this.name = name;
        this.password = new Password(passwordEncoder.encode(name.get()));
        this.role = role;
        this.state = WORK;
    }

    /**
     * # 퇴사
     */
    public void leave(){
        verifyWorking();
        state = LEAVE;
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
    }

    private void verifyLeaved() {
        if(state.equals(WORK)){
            throw new AlreadyWorkingException("이미 근무중인 사용자입니다.");
        }
    }

    /**
     * 조회 모델로 변환
     */
    public EmployeeModel toModel(){
        return EmployeeModel.builder()
                .name(name.get())
                .phone(phone.get())
                .state(state)
                .role(role)
                .password(password.get())
                .build();
    }
}
