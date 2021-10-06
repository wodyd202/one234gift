package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.domain.value.Writer;

import java.util.Optional;

public interface UserRepository {
    Optional<Writer> findUser();
}
