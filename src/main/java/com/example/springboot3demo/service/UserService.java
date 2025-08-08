package com.example.springboot3demo.service;

import com.example.springboot3demo.entity.User;
import com.example.springboot3demo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /*public User findById(long userId){
        User user = new User();
        user.setId(userId);
        user.setName("name@" + userId);


        return user;
    }

    public User save(User user) {

        user.setId(new Random().nextLong());
        user.setName(user.getName() + "@InService");
        return user;
    }*/

    @Resource
    private UserMapper userMapper;


    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
