package com.one234gift.orderservice.order.domain.value;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Content {
    private final static Content INSTNACE = new Content();
    private final String content;

    protected Content(){content=null;}

    public static Content getInstance() {
        return INSTNACE;
    }

    public Content(String content) {
        contentValidation(content);
        this.content = content;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎa-zA-Z0-9\\s]{1,100}$");

    private void contentValidation(String content) {
        if(!PATTERN.matcher(content).matches()){
            throw new IllegalArgumentException("비고는 [한글,숫자,영문](공백포함) 조합 1자 이상 100자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content1 = (Content) o;
        return Objects.equals(content, content1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
