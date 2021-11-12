package com.one234gift.orderservice.order.query.application;

import java.time.LocalDate;

public interface CalculateSalesRepository {
    Long findCumulativeSalesByUserId(String userId, LocalDate date);
}
