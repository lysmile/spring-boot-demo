package com.smile.demo.jwt.controller;

import com.alibaba.fastjson.JSON;
import com.smile.demo.jwt.entity.CodeResult;
import com.smile.demo.jwt.entity.User;
import com.smile.demo.jwt.exception.TokenException;
import com.smile.demo.jwt.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author smile
 */
@RestController
public class MainController {

    @Autowired
    private JwtTokenUtils tokenUtils;

    /**
     * 获取token
     */
    @PostMapping(value = "/demo/login")
    public CodeResult login(@RequestBody User user) throws TokenException {
        return new CodeResult("0", "success", JSON.toJSON(tokenUtils.createToken(user.getUsername(), user.getPassword())));
    }

    @PostMapping(value = "demo/test")
    public CodeResult test() {
        return new CodeResult("0", "success");
    }
}
