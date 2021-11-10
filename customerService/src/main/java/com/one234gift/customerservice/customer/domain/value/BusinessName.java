package com.one234gift.customerservice.customer.domain.value;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * 고객 업체명
 * # 필수 항목임
 */
@Embeddable
public class BusinessName {
    private final String name;

    protected BusinessName(){name=null;}

    public BusinessName(String name) {
        nameValidation(name);
        this.name = name;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1}[가-힣\\s]{0,28}[가-힣]{0,1}$");
    private void nameValidation(String name) {
        if(name == null){
            throw new IllegalArgumentException("고객 업체명을 입력해주세요.");
        }
        if(!PATTERN.matcher(name).matches()){
            throw new IllegalArgumentException("고객 업체명은 한글 조합 3자 이상 30자 이하(공백 허용)로 입력해주세요.");
        }
    }

    public String get() {
        return name;
    }
}
