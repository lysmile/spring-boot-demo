package com.smile.demo.springbootmybatisdemo.mapper;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    // 获取单个用户的信息
    UserInfo getUserInfoById(@Param("userId") String userId);

    // 获取多个用户的信息
    List<UserInfo> findList();

    // 保存单个用户
    void saveUser(UserInfo userInfo);

    // 批量保存用户
    void batchSaveUsers(List<UserInfo> userInfos);

    // 更新某个指定的字段
    void updateNameById(@Param("userId") String userId, @Param("userName") String userName);

    // 根据传过来的值灵活更新字段
    void updateById(UserInfo userInfo);

    // 删除
    void deleteById(@Param("userId") String userId);

    // 批量删除
    void batchDelByIds(List<String> userIds);

    // 插入或者更新，有则更新，无则插入
    void saveOrUpdate(UserInfo userInfo);
}
