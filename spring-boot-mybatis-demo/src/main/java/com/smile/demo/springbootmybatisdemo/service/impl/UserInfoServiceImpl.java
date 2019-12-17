package com.smile.demo.springbootmybatisdemo.service.impl;

import com.smile.demo.springbootmybatisdemo.mapper.UserInfoMapper;
import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import com.smile.demo.springbootmybatisdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoById(String userId) {
        return userInfoMapper.getUserInfoById(userId);
    }

    @Override
    public List<UserInfo> getUserInfoList() {
        return userInfoMapper.findList();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoMapper.saveUser(userInfo);
    }

    @Override
    public void updateNameById(String userId, String userName) {
        userInfoMapper.updateNameById(userId, userName);
    }

    @Override
    public void updateById(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public void saveUsers(List<UserInfo> userInfos) {
        userInfoMapper.batchSaveUsers(userInfos);
    }

    @Override
    public void deleteById(String userId) {
        userInfoMapper.deleteById(userId);
    }

    @Override
    public void deleteByIds(List<String> userIds) {
        userInfoMapper.batchDelByIds(userIds);
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        userInfoMapper.saveOrUpdate(userInfo);
    }
}
