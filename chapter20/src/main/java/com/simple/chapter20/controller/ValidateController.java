package com.simple.chapter20.controller;

import com.simple.chapter20.annotation.DateTime;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数校验
 *
 * @author yxxcoder
 * @since 2019-03-20 23:45 PM
 */
@Validated
@RestController
public class ValidateController {

    @GetMapping("/test")
    public String test(@DateTime(message = "您输入的格式错误，正确的格式为：{format}", format = "yyyy-MM-dd HH:mm") String date) {
        return "success";
    }

}
