package com.one234gift.orderservice.order.domain.value;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Price {
    private final long price;

    protected Price(){price=0;}

    public Price(long price) {
        priceValidation(price);
        this.price = price;
    }

    private void priceValidation(long price) {
        if(price < 1){
            throw new IllegalArgumentException("가격은 1원 이상 입력해주세요.");
        }
    }

    public long get() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price that = (Price) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
