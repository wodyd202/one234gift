package com.one234gift.orderservice.query.application.model;

import com.one234gift.orderservice.domain.read.SalesUserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class CalculateSalesModel {
    private SalesUserModel salesUser;
    private long totalSales;
}
