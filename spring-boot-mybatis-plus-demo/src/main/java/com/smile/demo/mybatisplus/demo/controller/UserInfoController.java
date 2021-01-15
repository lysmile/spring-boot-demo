package com.smile.demo.mybatisplus.demo.controller;


import com.smile.demo.mybatisplus.demo.entity.CodeResult;
import com.smile.demo.mybatisplus.demo.entity.QueryParam;
import com.smile.demo.mybatisplus.demo.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户详细信息 前端控制器
 * </p>
 *
 * @author Smile
 * @since 2021-01-14
 */
@RestController
public class UserInfoController {

    private final IUserInfoService userInfoService;

    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @PostMapping("user-info/list")
    public CodeResult listUserInfo(@RequestBody QueryParam param) {
        return userInfoService.listUserInfoByPaging(param.getPageNo(), param.getPageSize());
    }

    @GetMapping("user-info/get/{userId}")
    public CodeResult listUserInfo(@PathVariable String userId) {
        return userInfoService.getByUserId(userId);
    }

}
