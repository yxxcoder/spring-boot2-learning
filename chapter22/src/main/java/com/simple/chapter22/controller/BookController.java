package com.simple.chapter22.controller;

import com.simple.chapter22.annotation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 控制重复提交
 *
 * @author yxxcoder
 * @since 2019-03-22 09:19 PM
 */
@RestController
@RequestMapping("/books")
public class BookController {


    @LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(@RequestParam String token) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3L);
        return "success - " + token;
    }

}
