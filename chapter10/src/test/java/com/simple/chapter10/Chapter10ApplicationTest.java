package com.simple.chapter10;

import com.simple.chapter10.entity.User;
import com.simple.chapter10.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter10ApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(Chapter10ApplicationTest.class);

    @Autowired
    private UserService userService;


    /**
     * 从日志可以看出，查询是没有日志输出的，因为它直接从缓存中获取的数据
     * <p>
     * 控制台日志如下：
     * 进入 saveOrUpdate 方法
     * [saveOrUpdate] - [User{id=5, username='u5', password='p5'}]
     * [get] - [User{id=5, username='u5', password='p5'}]
     * 进入 delete 方法
     */
    @Test
    public void get() {
        final User user = userService.saveOrUpdate(new User(5L, "u5", "p5"));
        log.info("[saveOrUpdate] - [{}]", user);
        final User user1 = userService.get(5L);
        log.info("[get] - [{}]", user1);
        userService.delete(5L);
    }

}