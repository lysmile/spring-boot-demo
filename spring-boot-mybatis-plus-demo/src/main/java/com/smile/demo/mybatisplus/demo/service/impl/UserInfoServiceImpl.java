package com.smile.demo.mybatisplus.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smile.demo.mybatisplus.demo.entity.CodeResult;
import com.smile.demo.mybatisplus.demo.entity.UserInfo;
import com.smile.demo.mybatisplus.demo.mapper.UserInfoMapper;
import com.smile.demo.mybatisplus.demo.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smile.demo.mybatisplus.enums.ResponseEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户详细信息 服务实现类
 * </p>
 *
 * @author Smile
 * @since 2021-01-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public CodeResult listUserInfoByPaging(int pageNo, int pageSize) {
        return new CodeResult(ResponseEnum.SUCCESS, JSON.toJSON(super.page(new Page<>(pageNo, pageSize))));
    }

    @Override
    public CodeResult getByUserId(String userId) {
        UserInfo userInfo = super.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, userId));
        return new CodeResult(ResponseEnum.SUCCESS, JSON.toJSON(userInfo));
    }
}
