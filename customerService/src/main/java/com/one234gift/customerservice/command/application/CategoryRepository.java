package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.value.Category;

public interface CategoryRepository {
    boolean existByCategory(Category category);
}
