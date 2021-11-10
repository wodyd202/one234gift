package com.one234gift.customerservice.customer.command.infrastructure;

import com.one234gift.customerservice.customer.command.application.CategoryRepository;
import com.one234gift.customerservice.customer.domain.value.Category;
import org.springframework.stereotype.Repository;

@Repository
public class StubCategoryRepository implements CategoryRepository {
    @Override
    public boolean existByCategory(Category category) {
        return true;
    }
}
