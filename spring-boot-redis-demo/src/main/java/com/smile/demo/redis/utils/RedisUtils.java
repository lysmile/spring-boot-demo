package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 常用方法的工具类
 * @author smile
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 设置缓存失效时间
     * @param key 键
     * @param time 过期时间(单位：秒)
     * @return 成功返回true
     */
    public boolean expire(String key,long time){
        // 如果不大于0则会马上过期,没有意义,所以直接排除
        if(time <= 0) {
            throw new RuntimeException("过期时间必须大于0");
        }
        return redisTemplate.expire(key, Duration.ofSeconds(time));
    }

    /**
     * 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
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
        expire(key, time);
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
        expire(key, time);
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
     * @return
     */
    public double hincr(String key, String hashKey, double delta){
        return hashOperations.increment(key, hashKey, delta);
    }

    /**
     * hash递减
     * @param key 键
     * @param hashKey 项
     * @param delta 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String hashKey, double delta){
        return hashOperations.increment(key, hashKey, -delta);
    }

    /**
     * Set--根据key获取Set中的所有值
     * @param key 键
     * @return 值
     */
    public Set<Object> sget(String key){
        return setOperations.members(key);
    }

    /**
     * Set--根据value从一个set中查询是否存在
     * @param key 键
     * @param value 值
     * @return true-存在 false-不存在
     */
    public boolean sHasKey(String key, Object value){
        return setOperations.isMember(key, value);
    }

    /**
     * Set--插入
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return setOperations.add(key, values);
    }

    /**
     * Set--获取set缓存的长度
     * @param key 键
     * @return
     */
    public long getSetSize(String key){
        return setOperations.size(key);
    }

    /**
     * Set--删除set成员
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        return setOperations.remove(key, values);
    }

    /**
     * List--获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     */
    public List<Object> lget(String key, long start, long end){
        return listOperations.range(key, start, end);
    }

    /**
     * List--获取list缓存的长度
     * @param key 键
     */
    public long lGetListSize(String key){
        return listOperations.size(key);
    }

    /**
     * List--通过索引查找元素
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lgetByIndex(String key, long index){
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * List--插入
     * @param key 键
     * @param value 值
     */
    public void lpush(String key, Object value) {
        listOperations.rightPush(key, value);
    }


    /**
     * List--插入多个值
     * @param key 键
     * @param value 值
     * @return
     */
    public void lpushAll(String key, List<Object> value) {
        listOperations.rightPushAll(key, value);
    }


    /**
     * List--移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        return listOperations.remove(key, count, value);
    }

    /**
     * ZSet--添加元素
     * @param key
     * @param value
     * @param score
     * @return
     */
    public boolean zsetAdd(String key, Object value, double score) {
        return zSetOperations.add(key, value, score);
    }

    /**
     * ZSet--读取索引内的数据
     * @param key   键
     * @param startIndex 起始索引
     * @param stopIndex  终止索引， -1 代表获取所有的
     * @return
     */
    public Set<Object> zsetRangeByIndex(String key, long startIndex, long stopIndex) {
        return zSetOperations.range(key, startIndex, stopIndex);
    }

    /**
     * ZSet--获取score范围内的数据
     * @param key   键
     * @param start 开始
     * @param stop  结束
     * @return
     */
    public Set<Object> zsetRangeByScore(String key, double start, double stop) {
        return zSetOperations.rangeByScore(key, start, stop);
    }

    /**
     * ZSet--删除元素，支持一次删除多个
     * @param key    键
     * @param value  待删除的元素
     * @return 删除的元素个数
     */
    public long zsetRemove(String key, Object... value) {
        return zSetOperations.remove(key, value);
    }

    /**
     * ZSet--移除索引范围内的元素
     * @param key   键
     * @param start 开始
     * @param stop  终止
     * @return 删除的元素个数
     */
    public long zsetRemoveRange(String key, long start, long stop) {
        return zSetOperations.removeRange(key, start, stop);
    }

    /**
     * ZSet--移除Score范围内的元素
     * @param key
     * @param start
     * @param stop
     * @return 删除的元素个数
     */
    public long zsetRemoveByScore(String key, double start, double stop) {
        return zSetOperations.removeRangeByScore(key, start, stop);
    }

}
