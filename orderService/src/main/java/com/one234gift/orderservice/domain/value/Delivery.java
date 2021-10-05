package com.one234gift.orderservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Delivery {
    private final String addressDetail;

    protected Delivery(){addressDetail = null;}

    public Delivery(String addressDetail) {
        addressDetailValidation(addressDetail);
        this.addressDetail = addressDetail;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎa-zA-Z0-9\\s]{1,50}$");
    private void addressDetailValidation(String addressDetail) {
        if(!PATTERN.matcher(addressDetail).matches()){
            throw new IllegalArgumentException("배송지 정보는 [한글,숫자,영문](공백포함) 조합 1자 이상 50자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return addressDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(addressDetail, delivery.addressDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressDetail);
    }
}
