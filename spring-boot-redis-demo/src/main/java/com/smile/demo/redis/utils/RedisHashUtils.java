package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author smile
 */
@Component
public class RedisHashUtils {

    @Autowired
    private RedisCommonUtils commonUtils;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    /**
     * HashGet
     * @param key 键
     * @param hashKey hash的键
     * @return 值
     */
    public Object hget(String key, String hashKey){
        return hashOperations.get(key, hashKey);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<String,Object> hmget(String key){
        return hashOperations.entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     */
    public void hmset(String key, Map<String, Object> map){
        hashOperations.putAll(key, map);
    }

    /**
     * HashSet-插入多值，并设置过期时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 过期时间(单位：秒)
     */
    public void hmset(String key, Map<String, Object> map, long time){
        hashOperations.putAll(key, map);
        commonUtils.expire(key, time);
    }

    /**
     * HashSet-插入单个值
     * @param key 键
     * @param hashKey hash的键
     * @param value 值
     */
    public void hset(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param hashKey hash的键
     * @param value 值
     * @param time 过期时间(单位:秒)
     */
    public void hset(String key, String hashKey, Object value, long time) {
        hashOperations.put(key, hashKey, value);
        commonUtils.expire(key, time);
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param hashKeys hash的键
     */
    public void hdel(String key, Object... hashKeys){
        hashOperations.delete(key, hashKeys);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键
     * @param hashKey hash的键
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String hashKey){
        return hashOperations.hasKey(key, hashKey);
    }

    /**
     * hash递增
     * @param key 键
     * @param hashKey hash的键
     * @param delta 要增加几(大于0)
     * @return 递增操作后的值
     */
    public double hincr(String key, String hashKey, double delta){
        return hashOperations.increment(key, hashKey, delta);
    }

    /**
     * hash递减
     * @param key 键
     * @param hashKey 项
     * @param delta 减几
     * @return 递减操作后的值
     */
    public double hdecr(String key, String hashKey, double delta){
        return hashOperations.increment(key, hashKey, -delta);
    }
}
