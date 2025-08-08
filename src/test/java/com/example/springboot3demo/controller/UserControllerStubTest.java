package com.example.springboot3demo.controller;


import com.example.springboot3demo.controller.UserController;
import com.example.springboot3demo.entity.User;
import com.example.springboot3demo.service.UserService;
import com.example.springboot3demo.Springboot3DemoApplication;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 当需要测试controller接口的filter/advice或者返回值时，我们可能不需要实际执行service的业务逻辑，可以用这种测试方法。
 * 这种方式不会启动真正的web服务。
 * MockMvc是模拟发送api请求，但是controller中的api接口服务将不会执行完整的真业务逻辑，因为我们使用Mockito.when()等方法，模拟了service的业务方法的执行并返回了值。
 *
 * 那如何使得controller中调用service时，不实际执行业务逻辑呢？
 * 我们可以用@MockitoBean来声明service，并使用Mockito.when()等方法，来觉得service的业务方法返回的值
 *
 */
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = Springboot3DemoApplication.class)
public class UserControllerStubTest {
    @MockitoBean
    //表示模拟UserService的方法
    private UserService userService;

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        //表示当userService.findById()方法被调用时，直接返回mock值
        Mockito.when(userService.findById(any(Long.class))).thenReturn(user);

        //将不会会执行controller的service的业务逻辑
        mockMvc.perform(
                 MockMvcRequestBuilders.get("/api/user/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("mockUser");

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/user/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("admin", "admin")
                                .content("{\"name\": \"Jane Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))  //虽然接口的请求参数是：name="Jane Doe"，但是实际是返回mockUser
                .andExpect(jsonPath("$.name").value("mockUser"));
    }


}
