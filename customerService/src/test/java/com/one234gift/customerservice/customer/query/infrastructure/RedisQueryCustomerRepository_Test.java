package com.one234gift.customerservice.customer.query.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.CustomerFixture;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class RedisQueryCustomerRepository_Test {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.customer.key}")
    String CUSTOMER_KEY;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 고객_정보_저장(){
        Customer customer = CustomerFixture.aCustomer();
        CustomerModel customerModel = customer.toModel();

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        // 1은 db에 저장 후 자동으로 생성되는 시퀀스 값이라 가정함
        hashOperations.put(CUSTOMER_KEY, 1, customerModel);

        customerModel = objectMapper.convertValue(hashOperations.get(CUSTOMER_KEY, 1), CustomerModel.class);
        assertNotNull(customerModel);
    }
}
