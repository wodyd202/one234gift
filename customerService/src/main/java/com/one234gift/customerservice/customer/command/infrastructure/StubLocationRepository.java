package com.one234gift.customerservice.customer.command.infrastructure;

import com.one234gift.customerservice.customer.command.application.LocationRepository;
import com.one234gift.customerservice.customer.domain.value.Location;
import org.springframework.stereotype.Repository;

@Repository
public class StubLocationRepository implements LocationRepository {
    @Override
    public boolean existByLocation(Location location) {
        return true;
    }
}
