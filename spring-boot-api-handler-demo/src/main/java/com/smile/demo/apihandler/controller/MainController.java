package com.smile.demo.apihandler.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.smile.demo.apihandler.entity.CodeResult;
import com.smile.demo.apihandler.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smile
 */
@Api(tags = "接口测试")
@RestController
@ApiSort(102)
@Slf4j
@RequestMapping("v0.1")
public class MainController {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "测试接口")
    @GetMapping("first-demo")
    public CodeResult firstDemo(@RequestParam(value = "name") String name) {
        log.info("!!成功进入接口, 参数是:[{}]", name);
        return new CodeResult(ResponseEnum.SUCCESS, name);
    }
}