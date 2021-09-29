package com.one234gift.customerservice.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Tel {
    private final String tel;

    protected Tel() {tel = null;}

    public Tel(String tel) {
        telValidation(tel);
        this.tel = tel;
    }

    private final static Pattern PATTERN = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$");

    private void telValidation(String tel) {
        if(!PATTERN.matcher(tel).matches()){
            throw new IllegalArgumentException("연락처 형식은 [xxx-xxxx-xxxx] 형식으로 입력해주세요.");
        }
    }

    public String get() {
        return tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tel tel1 = (Tel) o;
        return Objects.equals(tel, tel1.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tel);
    }
}
