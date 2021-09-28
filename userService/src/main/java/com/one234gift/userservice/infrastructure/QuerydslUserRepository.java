package com.one234gift.userservice.infrastructure;

import com.one234gift.userservice.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.one234gift.userservice.domain.QUser.user;

@Repository
@Transactional
public class QuerydslUserRepository implements UserRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public void save(User user) {
        if(entityManager.contains(user)){
            entityManager.merge(user);
        }else{
            entityManager.persist(user);
        }
    }

    @Override
    public boolean existByPhone(Phone phone) {
        return jpaQueryFactory.selectOne()
            .from(user)
            .where(user.phone.eq(phone))
            .fetchFirst() != null;
    }
}
