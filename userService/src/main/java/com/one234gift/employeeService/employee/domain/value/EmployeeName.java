package com.one234gift.employeeService.employee.domain.value;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class EmployeeName {
    private final String name;

    protected EmployeeName(){
        name = null;
    }

    public EmployeeName(String name) {
        usernameValidation(name);
        this.name = name;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1,10}$");
    private void usernameValidation(String name) {
        if(!PATTERN.matcher(name).matches()){
            throw new IllegalArgumentException("사원의 이름은 한글 조합 1자 이상 10자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeName that = (EmployeeName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
