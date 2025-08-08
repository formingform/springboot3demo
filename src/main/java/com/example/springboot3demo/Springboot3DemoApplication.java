package com.example.springboot3demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@Slf4j
//如果Springboot3DemoApplication这个应用程序主类在根包，则可以不需要此注解，如果应用分了几个module，则需要
public class Springboot3DemoApplication implements ApplicationRunner  {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3DemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Springboot3DemoApplication started");
    }

}
