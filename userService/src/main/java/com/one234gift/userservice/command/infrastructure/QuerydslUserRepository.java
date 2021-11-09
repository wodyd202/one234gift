package com.one234gift.userservice.command.infrastructure;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.value.Phone;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.one234gift.userservice.domain.QUser.user;

@Repository
@Transactional
public class QuerydslUserRepository implements UserRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Autowired private JPAUserRepository jpaUserRepository;

    @Override
    public boolean existsByPhone(Phone phone) {
        return jpaQueryFactory.selectOne()
            .from(user)
            .where(eqPhone(phone))
            .fetchFirst() != null;
    }

    @Override
    public Optional<User> findByPhone(Phone phone) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(user)
                        .where(eqPhone(phone))
                        .fetchFirst()
        );
    }

    private BooleanExpression eqPhone(Phone phone){
        return user.phone.eq(phone);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(user);
    }
}
