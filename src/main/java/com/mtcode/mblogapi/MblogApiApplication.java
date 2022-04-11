package com.mtcode.mblogapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mtcode.mblogapi.mapper")
@EnableScheduling
public class MblogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MblogApiApplication.class, args);
    }

}
