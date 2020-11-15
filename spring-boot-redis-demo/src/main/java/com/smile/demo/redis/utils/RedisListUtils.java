package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author smile
 */
@Component
public class RedisListUtils {

    @Autowired
    private ListOperations<String, Object> listOperations;

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
        return listOperations.index(key, index);
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
}
