package com.one234gift.customerservice.config;

import feign.RequestInterceptor;
import feign.Retryer;
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
@EnableFeignClients
public class FeignClientConfig {
    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }

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
}
