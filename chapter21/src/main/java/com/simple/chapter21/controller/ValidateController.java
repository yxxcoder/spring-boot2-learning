package com.simple.chapter21.controller;

import com.simple.chapter21.groups.Groups;
import com.simple.chapter21.pojo.Book;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数校验
 *
 * @author yxxcoder
 * @since 2019-03-21 23:55 PM
 */

@RestController
public class ValidateController {

    @GetMapping("/insert")
    public String insert(@Validated(value = Groups.Default.class) Book book) {
        return "insert";
    }


    @GetMapping("/update")
    public String update(@Validated(value = {Groups.Default.class, Groups.Update.class}) Book book) {
        return "update";
    }
}
