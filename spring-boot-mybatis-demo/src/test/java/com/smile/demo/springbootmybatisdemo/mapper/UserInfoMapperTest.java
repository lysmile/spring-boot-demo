package com.smile.demo.springbootmybatisdemo.mapper;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * RunWith 指定当前运行环境
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Test
    public void findList() {
//        assertNull(userInfoMapper.findList());
        assertNotNull("如果输出结果不符合期望，这句话会显示出来", userInfoMapper.findList());
        // 期望值为2，如果实际输出值不等于期望值则报错
        assertEquals(2, userInfoMapper.findList().size());
    }

    @Test
    public void saveUser(){

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setUserName("张三");
        userInfo.setMail("test@test.com");
        userInfo.setPhone("12345678901");
        userInfo.setAddress("家里蹲大学");

    }

}