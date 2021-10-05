package com.one234gift.orderservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Product {
    private final String product;

    protected Product(){product=null;}

    public Product(String product) {
        productValidation(product);
        this.product = product;
    }

    private final static Pattern PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣0-9a-zA-Z\\s]{1,20}$");
    private void productValidation(String product) {
        if(!PATTERN.matcher(product).matches()){
            throw new IllegalArgumentException("상품명은 [한글,숫자,영어](공백 포함) 조합으로 1자이상 20자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product1 = (Product) o;
        return Objects.equals(product, product1.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
