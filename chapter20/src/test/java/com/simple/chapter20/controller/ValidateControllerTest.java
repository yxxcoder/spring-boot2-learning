package com.simple.chapter20.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidateControllerTest {

    @LocalServerPort
    private int port;

    private String testUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        this.testUrl = "http://localhost:" + port + "/test";
    }

    @Test
    public void test1() {
        String response = template.getForObject(testUrl + "?date={1}", String.class, "201903202355");
        Assert.assertTrue(response.contains("您输入的格式错误，正确的格式为"));

        String response2 = template.getForObject(testUrl + "?date={1}", String.class, "2019-03-20 23:55");
        Assert.assertTrue(response2.contains("success"));
    }
}