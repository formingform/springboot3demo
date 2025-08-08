package com.example.springboot3demo.service;

import com.example.springboot3demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    public User findById(long userId){
        User user = new User();
        user.setId(userId);
        user.setName("name@" + userId);


        return user;
    }

    public User save(User user) {

        user.setId(new Random().nextLong());
        user.setName(user.getName() + "@InService");
        return user;
    }
}
