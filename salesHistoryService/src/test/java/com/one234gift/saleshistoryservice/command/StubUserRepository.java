package com.one234gift.saleshistoryservice.command;

import com.one234gift.saleshistoryservice.command.application.UserRepository;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    @Override
    public Optional<Writer> findUser() {
        return Optional.of(Writer.builder()
                        .name("작성자")
                        .phone("000-0000-0000")
                .build());
    }
}
