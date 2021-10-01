package com.one234gift.customerservice.command.infrastructure;

import com.one234gift.customerservice.command.application.CategoryRepository;
import com.one234gift.customerservice.domain.value.Category;
import org.springframework.stereotype.Repository;

@Repository
public class StubCategoryRepository implements CategoryRepository {
    @Override
    public boolean existByCategory(Category category) {
        return true;
    }
}
