package com.simple.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 使用Spring Cache集成Redis
 *
 * @author yxxcoder
 * @since 2019-03-09 17:46 PM
 **/
@SpringBootApplication
@EnableCaching
public class Chapter10Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter10Application.class, args);
    }

}
