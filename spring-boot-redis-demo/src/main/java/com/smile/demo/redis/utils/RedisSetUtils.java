package com.smile.demo.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author smile
 */
@Component
public class RedisSetUtils {

    @Autowired
    private SetOperations<String, Object> setOperations;

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
}
