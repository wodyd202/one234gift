package com.one234gift.userservice.command.infrastructure;

import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static com.one234gift.userservice.domain.QUser.user;

@Repository
@Transactional
public class QuerydslUserRepository implements UserRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public boolean existByPhone(Phone phone) {
        return jpaQueryFactory.selectOne()
            .from(user)
            .where(user.phone.eq(phone))
            .fetchFirst() != null;
    }

    @Override
    public Optional<User> findByPhone(Phone phone) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(user)
                        .where(user.phone.eq(phone))
                        .fetchFirst()
        );
    }

    @Override
    public void save(User user) {
        if(entityManager.contains(user)){
            entityManager.merge(user);
        }else{
            entityManager.persist(user);
        }
    }
}
