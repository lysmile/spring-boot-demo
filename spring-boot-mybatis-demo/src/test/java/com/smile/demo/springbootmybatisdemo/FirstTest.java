package com.smile.demo.springbootmybatisdemo;

import com.smile.demo.springbootmybatisdemo.model.UserInfo;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirstTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getUserInfoList() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("张三");
        userInfo.setUserId("useriddddddd");
        userInfo.setMail("test@test.com");
        userInfo.setPhone("12345678901");
        userInfo.setAddress("家里蹲大学");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
