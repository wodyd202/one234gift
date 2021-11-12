package com.one234gift.orderservice.order.query.application.model;

import com.one234gift.orderservice.order.domain.read.SalesUserModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalculateSalesModel {
    private SalesUserModel salesUser;
    private long totalSales;
}
