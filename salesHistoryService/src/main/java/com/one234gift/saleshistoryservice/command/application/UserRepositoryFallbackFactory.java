package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.orderservice.command.application.UserRepository;
import com.one234gift.orderservice.domain.value.SalesUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserRepositoryFallbackFactory implements FallbackFactory<com.one234gift.orderservice.command.application.UserRepository> {
    @Override
    public com.one234gift.orderservice.command.application.UserRepository create(Throwable throwable) {
        return new UserRepository() {
            @Override
            public Optional<SalesUser> findUser() {
                log.info("user-service request error : {}", throwable.getMessage());
                return Optional.empty();
            }
        };
    }
}
