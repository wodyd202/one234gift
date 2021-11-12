package com.one234gift.employeeService.employee.domain.value;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Embeddable;

@Embeddable
public class Password {
    private final String password;

    protected Password(){password = null;}

    public Password(String password) {
        this.password = password;
    }

    public String get() {
        return password;
    }

    public Password encode(PasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder.encode(password));
    }
}
