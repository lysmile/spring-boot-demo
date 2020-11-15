package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 通用方法
 * @author smile
 */
@Component
public class RedisCommonUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存失效时间
     * @param key 键
     * @param time 过期时间(单位：秒) 如果不大于0则会马上过期
     * @return 成功返回true
     */
    public boolean expire(String key,long time){
        return redisTemplate.expire(key, Duration.ofSeconds(time));
    }

    /**
     * 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true-存在 false-不存在
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     * @param key 待删除键
     */
    public void del(String key){
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     * @param keys 待删除的键
     */
    public void delKeys(List<String> keys) {
        redisTemplate.delete(keys);
    }
}
