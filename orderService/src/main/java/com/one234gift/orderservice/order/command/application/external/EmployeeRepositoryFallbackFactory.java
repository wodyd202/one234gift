package com.one234gift.orderservice.order.command.application.external;

import com.one234gift.orderservice.order.domain.value.SalesUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserRepositoryFallbackFactory implements FallbackFactory<UserRepository> {
    @Override
    public UserRepository create(Throwable throwable) {
        return new UserRepository() {
            @Override
            public Optional<SalesUser> findUser(String userId) {
                log.info("user-service request error : {}", throwable.getMessage());
                return Optional.empty();
            }
        };
    }
}
