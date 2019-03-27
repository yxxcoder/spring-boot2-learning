package com.simple.chapter23.controller;

import com.simple.chapter23.annotation.CacheLock;
import com.simple.chapter23.annotation.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController
 *
 * @author yxxcoder
 * @since 2019-03-25 23:00
 */
@RestController
@RequestMapping("/books")
public class BookController {


    @CacheLock(prefix = "books")
    @GetMapping
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

}
