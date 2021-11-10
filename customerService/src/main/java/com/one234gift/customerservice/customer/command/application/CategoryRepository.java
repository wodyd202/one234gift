package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.domain.value.Category;

public interface CategoryRepository {
    boolean existByCategory(Category category);
}
