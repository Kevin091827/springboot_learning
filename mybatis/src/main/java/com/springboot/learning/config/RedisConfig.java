package com.springboot.learning.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:RedisConfig
 * @Description: TODO
 */
@Configuration
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * 序列化配置
     * @return
     */
    @Bean
    public RedisTemplate getRedisTemplate(){
        log.info("redis序列化配置");
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //默认序列化设置
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
