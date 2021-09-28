package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.Username;

import javax.persistence.Entity;

@Entity
public class AccountingUser extends User {
    public AccountingUser(Username username, Phone phone) {
        super(username,phone);
    }
}
