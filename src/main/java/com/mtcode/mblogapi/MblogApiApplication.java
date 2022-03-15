package com.mtcode.mblogapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mtcode.mblogapi.mapper")
public class MblogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MblogApiApplication.class, args);
    }

}
