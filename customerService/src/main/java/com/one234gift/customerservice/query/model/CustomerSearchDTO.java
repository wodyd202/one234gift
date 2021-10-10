package com.one234gift.customerservice.query.model;

import com.one234gift.customerservice.domain.value.SaleState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchDTO {
    private String businessName;
    private String location;
    private String category;
    private SaleState state;
}
