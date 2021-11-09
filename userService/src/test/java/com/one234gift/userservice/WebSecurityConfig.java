package com.one234gift.userservice;

import com.one234gift.userservice.domain.value.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/user/**/comeback").hasRole(UserRole.ACCOUNTING_USER.toString())
                .antMatchers(HttpMethod.DELETE,"/api/user/**").hasRole(UserRole.ACCOUNTING_USER.toString())
                .antMatchers(HttpMethod.POST,"/api/user").hasRole(UserRole.ACCOUNTING_USER.toString())
                .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
