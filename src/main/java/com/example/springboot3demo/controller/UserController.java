package com.example.springboot3demo.controller;

import com.example.springboot3demo.ApiScope;
import com.example.springboot3demo.entity.User;
import com.example.springboot3demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user =userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    @ApiScope(target = "manage")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.save(user);
        return ResponseEntity.status( HttpStatus.CREATED).body(createdUser);
    }
}
