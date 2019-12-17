package com.smile.demo.springbootmybatisdemo.service;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoService {

    UserInfo getUserInfoById(String userId);

    List<UserInfo> getUserInfoList();

    void save(UserInfo userInfo);

    void updateNameById(String userId, String userName);

    void updateById(UserInfo userInfo);

    // 批量保存用户
    void saveUsers(List<UserInfo> userInfos);

    // 删除
    void deleteById(@Param("userId") String userId);

    // 批量删除
    void deleteByIds(List<String> userIds);

    // 插入或者更新，有则更新，无则插入
    void saveOrUpdate(UserInfo userInfo);
}
