package com.smile.demo.mybatisplus.demo.service;

import com.smile.demo.mybatisplus.demo.entity.CodeResult;
import com.smile.demo.mybatisplus.demo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户详细信息 服务类
 * </p>
 *
 * @author Smile
 * @since 2021-01-14
 */
public interface IUserInfoService extends IService<UserInfo> {


    /**
     * 分页插件使用实例
     * @param pageNo
     * @param pageSize
     * @return
     */
    CodeResult listUserInfoByPaging(int pageNo, int pageSize);

    /**
     * 根据参数查找示例
     * @param userId
     * @return
     */
    CodeResult getByUserId(String userId);
}
