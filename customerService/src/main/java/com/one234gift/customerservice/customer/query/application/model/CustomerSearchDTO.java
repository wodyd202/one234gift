package com.one234gift.customerservice.customer.query.application.model;

import com.one234gift.customerservice.customer.domain.value.SaleState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerSearchDTO {
    @Pattern(message = "고객 업체명은 한글 조합 3자 이상 30자 이하(공백 허용)로 입력해주세요.", regexp = "^[가-힣]{1}[가-힣\\s]{0,28}[가-힣]{0,1}$")
    private String businessName;

    @Pattern(message = "지역은 [한글,숫자,영어](공백가능) 1자이상 15자 이하로 입력해주세요.", regexp = "^[가-힣0-9a-zA-Z\\s]{1,15}$")
    private String location;

    @Pattern(message = "분류는 한글조합 1자 이상 15자 이하만 허용합니다.", regexp = "^[가-힣]{1,15}$")
    private String category;

    private SaleState state;
}
