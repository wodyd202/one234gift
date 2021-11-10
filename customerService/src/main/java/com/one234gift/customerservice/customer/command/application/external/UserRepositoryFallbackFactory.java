package com.one234gift.customerservice.customer.command.application.external;

import com.one234gift.customerservice.customer.domain.value.Manager;
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
            public Optional<Manager> findUser(String userId) {
                log.info("sales-history-service request error : {}", throwable.getMessage());
                return Optional.of(Manager.builder().phone(userId).build());
            }
        };
    }
}
