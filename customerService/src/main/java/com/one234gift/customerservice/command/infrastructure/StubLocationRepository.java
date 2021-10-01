package com.one234gift.customerservice.command.infrastructure;

import com.one234gift.customerservice.command.application.LocationRepository;
import com.one234gift.customerservice.domain.value.Location;
import org.springframework.stereotype.Repository;

@Repository
public class StubLocationRepository implements LocationRepository {
    @Override
    public boolean existByLocation(Location location) {
        return true;
    }
}
