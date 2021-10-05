package com.one234gift.orderservice.domain.model;

import com.one234gift.orderservice.domain.value.OrderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrder {
    @NotBlank(message = "상품명을 입력해주세요.")
    @Pattern(message = "상품명은 [한글,숫자,영어](공백 포함) 조합으로 1자이상 20자 이하로 입력해주세요.", regexp = "^[ㄱ-ㅎ가-힣0-9a-zA-Z\\s]{1,20}$")
    private String product;

    @NotNull(message = "고객 고유번호를 입력해주세요.")
    private Long customerId;

    @Valid
    @NotNull(message = "배송지 정보를 입력해주세요.")
    private ChangeDelivery delivery;

    @Min(value = 1, message = "수량은 1개 이상 입력해주세요.")
    private long quantity;

    @Min(value = 1, message = "매입단가는 1원 이상 입력해주세요.")
    private long purchasePrice;

    @Min(value = 1, message = "판매단가는 1원 이상 입력해주세요.")
    private long salePrice;

    @Pattern(message = "비고는 [한글,숫자,영문](공백포함) 조합 1자 이상 100자 이하로 입력해주세요.", regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9\\s]{1,100}$")
    private String content;

    @NotNull(message = "주문 타입을 입력해주세요.")
    private OrderType type;

    @Builder
    public RegisterOrder(String product, Long customerId, ChangeDelivery delivery, long quantity, long purchasePrice, long salePrice, String content, OrderType type) {
        this.product = product;
        this.customerId = customerId;
        this.delivery = delivery;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.content = content;
        this.type = type;
    }
}
