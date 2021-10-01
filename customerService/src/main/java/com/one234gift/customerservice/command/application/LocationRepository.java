package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.value.Location;

public interface LocationRepository {
    boolean existByLocation(Location location);
}
