package com.one234gift.customerservice.domain.model;

import com.one234gift.customerservice.domain.value.CustomerReactivity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterSalesHistory {
    private boolean sample;
    private boolean catalogue;

    @NotBlank(message = "영업 내용을 입력해주세요.")
    @Pattern(message = "영업 내용은 [한글,영어,숫자](공백포함) 1자 이상 200자 이하로 입력해주세요.",regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s]{1,200}$")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate callReservationDate;

    @NotNull(message = "반응도를 입력해주세요.")
    private CustomerReactivity reactivity;

    @Builder
    public RegisterSalesHistory(boolean sample, boolean catalogue, String content, LocalDate callReservationDate, CustomerReactivity reactivity) {
        this.sample = sample;
        this.catalogue = catalogue;
        this.content = content;
        this.callReservationDate = callReservationDate;
        this.reactivity = reactivity;
    }
}
