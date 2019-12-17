package com.smile.demo.springbootmybatisdemo.controller;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import com.smile.demo.springbootmybatisdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserInfoController {

    private final String SUCCESS = "success";

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{userId}")
    public UserInfo getUserInfo(@PathVariable("userId") String userId){

        return userInfoService.getUserInfoById(userId);
    }

    @GetMapping("users")
    public List<UserInfo> getUserInfoList(){

        return userInfoService.getUserInfoList();
    }

    @PostMapping("save")
    public String save(@RequestBody UserInfo userInfo){
        userInfoService.save(userInfo);
        return SUCCESS;
    }

    // 批量保存用户
    @PostMapping("saveUsers")
    public String saveUsers(@RequestBody List<UserInfo> userInfos){
        userInfoService.saveUsers(userInfos);
        return SUCCESS;
    }

    @PostMapping("updateNameById")
    public String updateNameById(String userId, String userName){
        userInfoService.updateNameById(userId, userName);
        return SUCCESS;
    }

    @PostMapping("updateById")
    public String updateById(@RequestBody UserInfo userInfo){
        userInfoService.updateById(userInfo);
        return SUCCESS;
    }


    // 删除
    @PostMapping("deleteById")
    public String deleteById(String userId){
        userInfoService.deleteById(userId);
        return SUCCESS;
    }

    // 批量删除
    @PostMapping("deleteByIds")
    public String deleteByIds(@RequestBody Map<String, Object> map){
        List<String> userIds = (List<String>) map.get("userIds");
        userInfoService.deleteByIds(userIds);
        return SUCCESS;
    }

    // 插入或者更新，有则更新，无则插入
    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestBody UserInfo userInfo){
        userInfoService.saveOrUpdate(userInfo);
        return SUCCESS;
    }

}
