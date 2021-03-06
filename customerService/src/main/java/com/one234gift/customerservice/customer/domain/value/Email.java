package com.one234gift.customerservice.customer.domain.value;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Access(AccessType.FIELD)
public class Email {
    private static Email INSTANCE = new Email();
    private final String email;

    protected Email(){email = null;}

    public Email(String email) {
        emailValidation(email);
        this.email = email;
    }

    private final static Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");

    public static Email getInstance() {
        return INSTANCE;
    }

    private void emailValidation(String email) {
        if(!PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
    }

    public String get() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}
