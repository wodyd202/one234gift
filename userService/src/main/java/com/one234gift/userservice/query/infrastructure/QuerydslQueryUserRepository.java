package com.one234gift.userservice.query.infrastructure;

import com.one234gift.userservice.domain.QUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.query.application.QueryUserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuerydslQueryUserRepository implements QueryUserRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Override
    public Optional<UserModel> findByPhone(String phone) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.phone.eq(new Phone(phone)))
                .fetchFirst();
        if(user == null){
            return Optional.empty();
        }
        return Optional.of(user.toModel());
    }
}
