package com.simple.chapter23.controller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 运行前请启动 redis
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @LocalServerPort
    private int port;

    private String queryUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        this.queryUrl = "http://localhost:" + port + "/books";
    }

    @Test
    public void query() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> {
            String response = template.getForObject(queryUrl + "?token={1}", String.class, "123");
            try {
                Assert.assertTrue(response.contains("success - 123"));
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = template.getForObject(queryUrl + "?token={1}", String.class, "123");
            try {
                Assert.assertTrue(response.contains("重复提交"));
            } finally {
                countDownLatch.countDown();
            }
        }).start();


        new Thread(() -> {
            // 等待上一次请求结束
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = template.getForObject(queryUrl + "?token={1}", String.class, "123");
            try {
                Assert.assertTrue(response.contains("success - 123"));
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();
    }
}