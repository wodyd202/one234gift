package com.one234gift.employeeService.employee.domain.value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Phone implements Serializable {
    private final String phone;

    protected Phone(){phone=null;}

    public Phone(String phone) {
        phoneValidation(phone);
        this.phone = phone;
    }

    private final static Pattern PATTERN = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
    private void phoneValidation(String phone) {
        if(!PATTERN.matcher(phone).matches()){
            throw new IllegalArgumentException("전화번호 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.");
        }
    }

    public String get() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return Objects.equals(phone, phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
