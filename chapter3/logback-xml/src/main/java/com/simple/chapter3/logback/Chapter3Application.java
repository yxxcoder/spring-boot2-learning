package com.simple.chapter3.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Logback日志配置
 *
 * @author yxxcoder
 * @create 2019-01-26 11:43 PM
 **/
@SpringBootApplication
public class Chapter3Application {

    private static Logger logger = LoggerFactory.getLogger(Chapter3Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
        logger.debug("Start Success..");
    }
}
