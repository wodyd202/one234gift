package com.one234gift.customerservice.customer.domain.value;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * 사업자 번호
 * # 필수 항목이 아님
 */
@Embeddable
public class BusinessNumber {
    private final static BusinessNumber INSTANCE = new BusinessNumber();
    private final String number;

    public static BusinessNumber getInstance() {
        return INSTANCE;
    }

    protected BusinessNumber(){
        number = null;
    }

    public BusinessNumber(String number) {
        if(number == null){
            this.number = null;
            return;
        }
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
