package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.config.FeignClientConfig;
import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE",
             fallbackFactory = UserRepositoryFallbackFactory.class,
             configuration = FeignClientConfig.class)
public interface UserRepository {
    @GetMapping("api/user")
    Optional<SalesUser> findUser();
}
