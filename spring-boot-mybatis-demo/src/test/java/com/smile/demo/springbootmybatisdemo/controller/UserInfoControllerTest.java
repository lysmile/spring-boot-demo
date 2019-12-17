package com.smile.demo.springbootmybatisdemo.controller;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setUserName("张三_Test");
        userInfo.setMail("test@test.com");
        userInfo.setPhone("12345678901");
        userInfo.setAddress("家里蹲大学");
    }

    @Test
    public void getUserInfo() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void getUserInfoList() {
    }

    @Test
    public void save() {
    }

    @Test
    public void updateNameById() {
    }
}