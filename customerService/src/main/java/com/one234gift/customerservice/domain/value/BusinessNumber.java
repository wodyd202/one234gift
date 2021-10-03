package com.one234gift.customerservice.domain.value;

import java.util.regex.Pattern;

public class BusinessNumber {
    private final String number;

    protected BusinessNumber(){
        number = null;
    }

    public BusinessNumber(String number) {
        businessNumberValidation(number);
        this.number = number;
    }

    private final static Pattern PATTERN = Pattern.compile("^(\\d{3,3})+[-]+(\\d{2,2})+[-]+(\\d{5,5})");
    private void businessNumberValidation(String number) {
        if(!PATTERN.matcher(number).matches()){
            throw new IllegalArgumentException("사업자번호는 [xxx-xx-xxxxx] 양식으로 입력해주세요.");
        }
    }

    public String get() {
        return number;
    }
}
