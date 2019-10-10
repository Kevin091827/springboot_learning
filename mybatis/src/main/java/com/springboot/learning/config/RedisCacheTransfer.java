package com.springboot.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:RedisCacheTransfer
 * @Description: TODO
 */
@Component
public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
        MybatisCacheConfig.setJedisConnectionFactory(jedisConnectionFactory);
    }

}
