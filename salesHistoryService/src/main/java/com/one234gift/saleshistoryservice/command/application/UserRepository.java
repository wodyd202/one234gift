package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserRepository {
    @GetMapping("api/user")
    Optional<Writer> findUser();
}
