package com.one234gift.saleshistoryservice.command.application.external;

import com.one234gift.saleshistoryservice.config.FeignClientConfig;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE",
             fallbackFactory = UserRepositoryFallbackFactory.class,
             configuration = FeignClientConfig.class)
public interface UserRepository {
    @GetMapping("api/user")
    Optional<Writer> findUser();
}
