package com.example.springboot3demo.controller;


import com.example.springboot3demo.entity.User;
import com.example.springboot3demo.Springboot3DemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//使用mockMvc进行测试http请求。启动了完整的Spring应用程序上下文，但没有启动WEB服务器

/**
 * 当我们像全流程测试controller的执行逻辑，而又不想先启动应用，然后通过curl或者postman来测试controller中接口时，可以用这种方式来执行单元测试。
 * 这种方式不会启动真正的web服务
 * MockMvc是模拟发送api请求，但是controller中的api接口服务将会执行完整的真实的的业务逻辑
 *
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Springboot3DemoApplication.class)
public class UserControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {

        mockMvc.perform(
                 MockMvcRequestBuilders.get("/api/user/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name@1"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User request = new User();
        request.setName("Jane Doe");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("admin", "admin")
                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Jane Doe@InService")); //虽然接口的请求参数是：name="Jane Doe"，但是经service处理，实际是返回Jane Doe@InService

    }

}
