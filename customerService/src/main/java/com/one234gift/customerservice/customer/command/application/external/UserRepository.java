package com.one234gift.customerservice.customer.command.application.external;

import com.one234gift.customerservice.customer.domain.value.Manager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserRepository {
    @GetMapping("api/user/{userId}")
    Optional<Manager> findUser(@PathVariable String userId);
}
