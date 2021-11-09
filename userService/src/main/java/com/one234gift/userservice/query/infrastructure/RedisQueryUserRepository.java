package com.one234gift.userservice.query.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.query.application.QueryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public class RedisQueryUserRepository implements QueryUserRepository {
    @Autowired private RedisTemplate<String, Object> redisTemplate;
    @Autowired private ObjectMapper objectMapper;
    private HashOperations hashOperations;

    @Value("${redis.user.key}")
    private String USER_KEY;

    @PostConstruct
    void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(UserModel userModel) {
        hashOperations.put(USER_KEY, userModel.getPhone(), userModel);
    }

    public Optional<UserModel> findByPhone(String phone) {
        Object obj = hashOperations.get(USER_KEY, phone);
        if(obj != null){
            return Optional.of(objectMapper.convertValue(obj, UserModel.class));
        }
        return Optional.empty();
    }
}
