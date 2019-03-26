package com.simple.chapter23;

import com.simple.chapter23.interceptor.CacheKeyGenerator;
import com.simple.chapter23.interceptor.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * 轻松搞定重复提交（分布式锁）
 *
 * @author yxxcoder
 * @since 2019-03-25 23:00 PM
 */
@SpringBootApplication
public class Chapter23Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter23Application.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }

}