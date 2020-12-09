package com.smile.demo.multidb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@RestController
public class MainController {

    @Autowired
    private RedisTemplate<String, Object> db0RedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> db1RedisTemplate;

    @GetMapping("test")
    public String testMultiDbOperation() {
        db0RedisTemplate.opsForValue().set("db0_test", "test db0");
        db1RedisTemplate.opsForValue().set("db1_test", "test db1");
        return "success";
    }

}
