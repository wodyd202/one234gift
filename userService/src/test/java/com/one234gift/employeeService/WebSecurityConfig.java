package com.one234gift.employeeService;

import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
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
                .antMatchers("/api/employee/**/comeback").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .antMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .antMatchers(HttpMethod.POST,"/api/employee").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
