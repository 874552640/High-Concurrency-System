package com.example.high;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.high.mapper")
public class HighApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighApplication.class, args);
    }

}
