package com.one234gift.customerservice.customer.domain.value;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Access(AccessType.FIELD)
public class ManagerName {
    private final String managerName;

    protected ManagerName(){managerName = null;}

    public ManagerName(String managerName) {
        managerNameValidation(managerName);
        this.managerName = managerName;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1,10}$");
    private void managerNameValidation(String managerName) {
        if(!PATTERN.matcher(managerName).matches()){
            throw new IllegalArgumentException("구매담당자 이름은 한글조합 1자 이상 10자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return managerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerName that = (ManagerName) o;
        return Objects.equals(managerName, that.managerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerName);
    }

    @Override
    public String toString() {
        return "ManagerName{" +
                "managerName='" + managerName + '\'' +
                '}';
    }
}
