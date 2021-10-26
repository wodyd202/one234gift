package com.one234gift.customerhistoryservice.config;

import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.host}")
    private String host;

    private RedisServer redisServer;

    @PreDestroy
    public void destroy() {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
    }

    @PostConstruct
    public void afterPropertiesSet() {
        redisServer = new RedisServer(port);
        try{
            redisServer.start();
        }catch (Exception e){
        }
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    RedisTemplate<String,CustomerHistory> redisTemplate(){
        RedisTemplate<String, CustomerHistory> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
