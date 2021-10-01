package com.one234gift.customerservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Category {
    private final String category;

    protected Category(){category = null;}

    public Category(String category) {
        categoryValidation(category);
        this.category = category;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1,15}$");
    private void categoryValidation(String category) {
        if(!PATTERN.matcher(category).matches()){
            throw new IllegalArgumentException("분류는 한글조합 1자 이상 15자 이하만 허용합니다.");
        }
    }

    public String get() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
