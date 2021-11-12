package com.one234gift.userservice.command.infrastructure;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends JpaRepository<User, Phone> {
}
