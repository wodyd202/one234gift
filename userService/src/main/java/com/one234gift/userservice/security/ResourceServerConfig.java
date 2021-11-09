package com.one234gift.userservice.security;

import com.one234gift.userservice.domain.value.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@Profile("!test")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/**/comeback").hasRole(UserRole.ACCOUNTING_USER.toString())
                .antMatchers(HttpMethod.DELETE,"/api/user/**").hasRole(UserRole.ACCOUNTING_USER.toString())
                .antMatchers(HttpMethod.POST,"/api/user").hasRole(UserRole.ACCOUNTING_USER.toString())
                .anyRequest().authenticated();
    }
}
