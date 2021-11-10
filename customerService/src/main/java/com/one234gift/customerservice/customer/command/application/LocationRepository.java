package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.domain.value.Location;

public interface LocationRepository {
    boolean existByLocation(Location location);
}
