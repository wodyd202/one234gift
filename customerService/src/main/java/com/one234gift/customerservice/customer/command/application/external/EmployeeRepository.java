package com.one234gift.customerservice.customer.command.application.external;

import com.one234gift.customerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "USER-SERVICE", configuration = FeignClientConfig.class, fallbackFactory = UserRepositoryFallbackFactory.class)
public interface UserRepository {
    @GetMapping("api/user/{userId}")
    Optional<Employee> findUser(@PathVariable String userId);
}
