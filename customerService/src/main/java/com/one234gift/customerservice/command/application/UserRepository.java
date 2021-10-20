package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.value.Manager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserRepository {
    @GetMapping("api/user")
    Optional<Manager> findUser();
}
