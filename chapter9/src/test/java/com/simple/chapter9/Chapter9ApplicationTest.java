package com.simple.chapter9;


import com.simple.chapter9.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author yxxcoder
 * @create 2019-03-08 17:29 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter9ApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(Chapter9ApplicationTest.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;


    /**
     * Redis其它类型所对应的操作方式
     * opsForValue： 对应 String（字符串）
     * opsForZSet： 对应 ZSet（有序集合）
     * opsForHash： 对应 Hash（哈希）
     * opsForList： 对应 List（列表）
     * opsForSet： 对应 Set（集合）
     * opsForGeo： 对应 GEO（地理位置）
     */
    @Test
    public void get() {
        // 清理缓存
        stringRedisTemplate.delete("kk");

        // 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        final String kk = stringRedisTemplate.opsForValue().get("kk");
        log.info("[线程安全测试结果] - [{}]", kk);


        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);

        // 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "simple:user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "u1", "pa"));
        // 对应 String（字符串）
        final User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user);
    }

}
