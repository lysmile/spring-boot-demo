package com.smile.demo.apihandler.controller;

import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.smile.demo.apihandler.entity.CodeResult;
import com.smile.demo.apihandler.entity.User;
import com.smile.demo.apihandler.enums.ResponseEnum;
import com.smile.demo.apihandler.exception.TokenException;
import com.smile.demo.apihandler.utils.JwtUtils;
import com.smile.demo.apihandler.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@RestController
@RequestMapping("token")
@Api(value = "token", tags="token")
@ApiSort(101)
public class TokenController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("get")
    @ApiOperationSupport(author = "smile")
    @ApiOperation(value = "获取token")
    public CodeResult getToken(@RequestBody @Validated User user, BindingResult bindingResult) throws TokenException {
        ValidatorUtils.handleBindingResult(bindingResult);
        return new CodeResult(ResponseEnum.SUCCESS, JSON.toJSON(jwtUtils.createToken(user.getUsername(), user.getPassword())));
    }

}
