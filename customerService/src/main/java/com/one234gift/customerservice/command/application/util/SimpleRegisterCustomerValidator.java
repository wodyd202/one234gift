package com.one234gift.customerservice.command.application.util;

import com.one234gift.customerservice.command.application.CategoryRepository;
import com.one234gift.customerservice.command.application.LocationRepository;
import com.one234gift.customerservice.command.application.exception.CategoryNotFoundException;
import com.one234gift.customerservice.command.application.exception.LocationNotFoundException;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.value.Address;
import com.one234gift.customerservice.domain.value.Category;
import com.one234gift.customerservice.domain.value.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimpleRegisterCustomerValidator implements RegisterCustomerValidator {
    private CategoryRepository categoryRepository;
    private LocationRepository locationRepository;

    @Override
    public void validation(Category category, Address address) {
        if(!existsCategory(category)){
            throw new CategoryNotFoundException();
        }
        Location location = address.getLocation();
        if(!existLocation(location)){
            throw new LocationNotFoundException();
        }
    }

    private boolean existsCategory(Category category) {
        return categoryRepository.existByCategory(category);
    }

    private boolean existLocation(Location location) {
        return locationRepository.existByLocation(location);
    }
}
