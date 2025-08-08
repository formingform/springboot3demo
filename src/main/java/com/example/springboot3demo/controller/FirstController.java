package com.example.springboot3demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {



    @RequestMapping("/hello")
    String hello() {
        return "First Spring Boot 3 Application!";
    }



}
