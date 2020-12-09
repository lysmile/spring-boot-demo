package com.smile.demo.multidb.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置
 * @author smile
 */
@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.pool.max-total}")
    private int maxTotal;
    @Value("${redis.pool.max-wait}")
    private int maxWait;
    @Value("${redis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.pool.min-idle}")
    private int minIdle;

    @Bean(name = "db0RedisTemplate")
    public RedisTemplate<String, Object> db0RedisTemplate() {
        return getRedisTemplate(db0Factory());
    }
    @Bean(name = "db1RedisTemplate")
    public RedisTemplate<String, Object> db1RedisTemplate() {
        return getRedisTemplate(db1Factory());
    }

    @Bean
    @Primary
    public LettuceConnectionFactory db0Factory(){
        return new LettuceConnectionFactory(getRedisConfig(0), getClientConfig());
    }

    @Bean
    public LettuceConnectionFactory db1Factory(){
        return new LettuceConnectionFactory(getRedisConfig(1), getClientConfig());
    }
    private RedisStandaloneConfiguration getRedisConfig(int dbNo) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(password);
        config.setDatabase(dbNo);
        return config;
    }

    private LettuceClientConfiguration getClientConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        return LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();
    }

    public RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory factory) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(String.class);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    public StringRedisTemplate getStringRedisTemplate(LettuceConnectionFactory factory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
