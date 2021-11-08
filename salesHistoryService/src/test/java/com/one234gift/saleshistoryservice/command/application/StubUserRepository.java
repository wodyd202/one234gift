package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.external.UserRepository;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    private Writer writer;

    @Override
    public Optional<Writer> findUser() {
        return Optional.of(writer);
    }

    public void save(String userId){
        writer = Writer.builder()
                .phone(userId)
                .name(userId)
                .build();
    }
}
