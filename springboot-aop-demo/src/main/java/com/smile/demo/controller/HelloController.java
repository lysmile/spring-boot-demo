package com.smile.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunqiang
 */
@RestController
@RequestMapping("/demo/aop")
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello World!";
    }

    @GetMapping("hello2")
    public String hello2() {
        int i = 1 / 0;
        return "测试报错的AOP方法";
    }
}
