package com.one234gift.orderservice.domain.value;

import java.util.Objects;

public class OrderQuantity {
    private final long quantity;

    public OrderQuantity(long quantity) {
        quantityValidation(quantity);
        this.quantity = quantity;
    }

    private void quantityValidation(long quantity) {
        if(quantity < 1){
            throw new IllegalArgumentException("수량은 1개 이상 입력해주세요.");
        }
    }

    public long get() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderQuantity that = (OrderQuantity) o;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
