package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.value.Password;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.Username;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class AccountingUser extends User {
    protected AccountingUser(){}
    protected AccountingUser(Username username, Phone phone, Password password) {
        super(username,phone, password);
    }
}
