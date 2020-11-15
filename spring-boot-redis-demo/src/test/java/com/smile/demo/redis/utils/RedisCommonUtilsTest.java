package com.smile.demo.redis.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisCommonUtilsTest {

    @Autowired
    private RedisCommonUtils commonUtils;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "expire:test";

    @Test
    void expire() {
        commonUtils.expire(KEY, 60);
    }

    @Test
    void getExpire() {
        System.out.println(commonUtils.getExpire(KEY));
    }

    @Test
    void hasKey() {
        System.out.println(commonUtils.hasKey(KEY));
    }

    @Test
    void del() {
    }

    @Test
    void delKeys() {
    }

    @Test
    void selectDb() {
        // db 10
    }
}