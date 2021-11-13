package com.one234gift.saleshistoryservice.salesHistory.domain.value;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class HistoryContent {
    private final String content;

    protected HistoryContent(){content = null;}

    public HistoryContent(String content) {
        contentValidation(content);
        this.content = content;
    }

    private final static Pattern PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9\\s]{1,200}$");
    private void contentValidation(String content) {
        if(!PATTERN.matcher(content).matches()){
            throw new IllegalArgumentException("영업 내용은 [한글,영어,숫자](공백포함) 1자 이상 200자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryContent that = (HistoryContent) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
