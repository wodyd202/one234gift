package com.one234gift.userservice.domain;

import com.one234gift.userservice.domain.value.Password;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.Username;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class SalesUser extends User{
    protected SalesUser(){}
    protected SalesUser(Username username, Phone phone, Password password) {
        super(username, phone, password);
    }
}
