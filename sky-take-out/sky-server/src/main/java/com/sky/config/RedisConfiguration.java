package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Configuration
 */
@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("Start building redis template");

        RedisTemplate redisTemplate = new RedisTemplate();

        //** Set redis connection factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //** Set Key Serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());


        return redisTemplate;
    }
}
