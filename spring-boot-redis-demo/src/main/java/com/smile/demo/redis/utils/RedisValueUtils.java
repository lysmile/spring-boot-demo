package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

/**
 * 普通缓存操作
 * @author smile
 */
public class RedisValueUtils {

    @Autowired
    private ValueOperations<String, Object> valueOperations;


    /**
     * 读取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return valueOperations.get(key);
    }

    /**
     * 设置普通缓存
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    /**
     * 带过期时间的缓存
     * @param key 键
     * @param value 值
     * @param time 过期时间(单位：秒)，过期时间必须大于0
     */
    public void set(String key, Object value, long time){
        if (time > 0) {
            valueOperations.set(key, value, Duration.ofSeconds(time));
        }else {
            throw new RuntimeException("过期时间必须大于0");
        }

    }

    /**
     * 计数器-递增,每次+1
     * @param key 键
     * @return 自增操作后的值
     */
    public long incr(String key){
        return valueOperations.increment(key);
    }

    /**
     * 计数器-递增
     * @param key 键
     * @param delta 每次递增的值
     * @return 递增操作后的值
     */
    public long incr(String key, long delta){
        if(delta > 0){
            return valueOperations.increment(key, delta);
        }
        throw new RuntimeException("递增因子必须大于0");
    }

    /**
     * 递减
     * @param key 键
     * @return 操作后的值
     */
    public long decr(String key){
        return valueOperations.decrement(key);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 每次减少的值
     * @return 操作后的值
     */
    public long decr(String key, long delta){
        if(delta > 0){
            valueOperations.decrement(key, delta);
        }
        throw new RuntimeException("递减因子必须大于0");
    }
}
