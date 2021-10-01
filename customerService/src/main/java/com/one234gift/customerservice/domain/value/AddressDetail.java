package com.one234gift.customerservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class AddressDetail {
    private final String detail;

    protected AddressDetail() {
        detail = null;
    }

    public AddressDetail(String detail) {
        detailValdiation(detail);
        this.detail = detail;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z0-9\\s]{1,20}$");

    private void detailValdiation(String detail) {
        if(!PATTERN.matcher(detail).matches()){
            throw new IllegalArgumentException("상세 주소는 [한글,숫자,영어](공백가능) 1자이상 20자이하로 입력해주세요.");
        }
    }

    public String get() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDetail that = (AddressDetail) o;
        return Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detail);
    }
}
