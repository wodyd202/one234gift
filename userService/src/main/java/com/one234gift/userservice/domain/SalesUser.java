package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.Username;

import javax.persistence.Entity;

@Entity
public class SalesUser extends User{

    protected SalesUser(Username username, Phone phone) {
        super(username, phone);
    }
}
