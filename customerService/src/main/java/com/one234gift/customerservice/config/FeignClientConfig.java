package com.one234gift.customerservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Profile("!test")
@Configuration
@EnableFeignClients(basePackages = "com.one234gift.customerservice")
public class FeignClientConfig {

    @Bean
    RequestInterceptor requestInterceptor(){
        String AUTHORIZATION_HEADER = "Authorization";
        String TOKEN_TYPE = "Bearer";

        return (requestTemplate)->{
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication instanceof OAuth2Authentication) {
                OAuth2AuthenticationDetails token = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, token.getTokenValue()));
            }
        };
    }

    @Bean
    Feign.Builder feignBuilder(){
        SetterFactory setterFactory = (target, method) -> HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.name()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(Feign.configKey(target.type(), method)))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withMetricsRollingStatisticalWindowInMilliseconds(10000) // 기준시간
                        .withCircuitBreakerSleepWindowInMilliseconds(3000) // 서킷 열려있는 시간
                        .withCircuitBreakerErrorThresholdPercentage(50) // 에러 비율 기준 퍼센트
                        .withCircuitBreakerRequestVolumeThreshold(5)); // 최소 호출 횟수
        return HystrixFeign.builder()
                .logLevel(Logger.Level.BASIC)
                .setterFactory(setterFactory);
    }
}
