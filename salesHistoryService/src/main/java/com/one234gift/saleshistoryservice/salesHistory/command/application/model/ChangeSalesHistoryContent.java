package com.one234gift.saleshistoryservice.salesHistory.command.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeSalesHistoryContent {
    @NotBlank(message = "영업 내용을 입력해주세요.")
    @Pattern(message = "영업 내용은 [한글,영어,숫자](공백포함) 1자 이상 200자 이하로 입력해주세요.",regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s]{1,200}$")
    private String content;

    @Builder
    public ChangeSalesHistoryContent(String content) {
        this.content = content;
    }
}
