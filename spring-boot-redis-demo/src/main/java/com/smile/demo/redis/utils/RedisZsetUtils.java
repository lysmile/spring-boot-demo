package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author smile
 */
@Component
public class RedisZsetUtils {

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

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
