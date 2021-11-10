package com.one234gift.customerservice.customer.domain;

import com.one234gift.customerservice.customer.domain.value.Address;
import com.one234gift.customerservice.customer.domain.value.Category;

public interface RegisterCustomerValidator {
    void validation(Category category, Address address);
}
