package com.one234gift.customerservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeSalesHistoryContent {
    private String content;

    @Builder
    public ChangeSalesHistoryContent(String content) {
        this.content = content;
    }
}
