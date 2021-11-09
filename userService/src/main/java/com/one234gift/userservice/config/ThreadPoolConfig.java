package com.one234gift.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Profile("!test")
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "queryUserExecutor")
    Executor userExcutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setQueueCapacity(5);
        threadPoolTaskExecutor.setThreadNamePrefix("queryUserExecutor-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
