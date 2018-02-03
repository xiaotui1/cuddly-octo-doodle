package com.heima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * xuan
 * 2018/2/2
 */
@SpringBootApplication
@MapperScan("com.heima.mapper")
public class MyStart {
    public static void main(String[] args) {
        SpringApplication.run(MyStart.class, args);
    }
}
