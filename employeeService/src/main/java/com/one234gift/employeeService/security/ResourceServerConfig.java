package com.one234gift.employeeService.security;

import com.one234gift.employeeService.employee.domain.value.EmployeePosition;
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
                .antMatchers("/api/employee/**/comeback").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .antMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .antMatchers(HttpMethod.POST,"/api/employee").hasRole(EmployeePosition.ACCOUNTING_EMPLOYEE.toString())
                .anyRequest().authenticated();
    }
}
