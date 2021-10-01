package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.value.Address;
import com.one234gift.customerservice.domain.value.Category;

public interface RegisterCustomerValidator {
    void validation(Category category, Address address);
}
