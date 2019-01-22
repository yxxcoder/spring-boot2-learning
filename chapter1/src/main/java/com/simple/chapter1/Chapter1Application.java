package com.simple.chapter1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 构建第一个Spring Boot工程
 *
 * @author yxxcoder
 * @create 2019-01-18 11:58 PM
 **/
@RestController
@SpringBootApplication
public class Chapter1Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter1Application.class, args);
    }

    @GetMapping("/demo1")
    public String demo1() {
        return "Hello";
    }

    /**
     * CommandLineRunner接口主要用于实现在应用初始化后，去执行一段代码块逻辑
     * 这段初始化代码在整个应用生命周期内只会执行一次
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(System.out::println);
        };
    }
}
