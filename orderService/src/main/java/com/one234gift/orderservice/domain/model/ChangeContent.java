package com.one234gift.orderservice.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeContent {
    private String content;

    @Builder
    public ChangeContent(String content) {
        this.content = content;
    }
}
