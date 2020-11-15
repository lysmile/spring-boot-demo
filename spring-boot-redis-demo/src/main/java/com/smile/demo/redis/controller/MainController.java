package com.smile.demo.redis.controller;

import com.smile.demo.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@RestController
@RequestMapping("redis-demo")
public class MainController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("string/{key}")
    public Object stringDemo(@PathVariable String key) {
        return redisUtils.get(key);
    }


}
