package com.simple.chapter19.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidateControllerTest {

    @LocalServerPort
    private int port;

    private String testUrl1;
    private String testUrl2;
    private String testUrl3;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        this.testUrl1 = "http://localhost:" + port + "/test1";
        this.testUrl2 = "http://localhost:" + port + "/test2";
        this.testUrl3 = "http://localhost:" + port + "/test3";
    }

    @Test
    public void test1() {
        String response = template.getForObject(testUrl1, String.class);
        Assert.assertTrue(response.contains("name 不能为空"));

        String response2 = template.getForObject(testUrl1 + "?name={1}", String.class, "1");
        Assert.assertTrue(response2.contains("name 长度必须在 2 - 10 之间"));

        String response3 = template.getForObject(testUrl1 + "?name={1}", String.class, "Jerry");
        Assert.assertTrue(response3.contains("success"));
    }

    @Test
    public void test2() {
        String response = template.getForObject(testUrl2, String.class);
        Assert.assertTrue(response.contains("name 不能为空"));

        String response2 = template.getForObject(testUrl2 + "?name={1}", String.class, "1");
        Assert.assertTrue(response2.contains("name 长度必须在 2 - 10 之间"));

        String response3 = template.getForObject(testUrl2 + "?name={1}", String.class, "Jerry");
        Assert.assertTrue(response3.contains("success"));
    }

    @Test
    public void test3() {
        String response = template.getForObject(testUrl3 + "?name={1}&price={2}", String.class, null, null);
        Assert.assertTrue(response.contains("name 不允许为空"));
        Assert.assertTrue(response.contains("price 不允许为空"));

        String response2 = template.getForObject(testUrl3 + "?name={1}&price={2}", String.class, "1", 0.01);
        Assert.assertTrue(response2.contains("name 长度必须在"));
        Assert.assertTrue(response2.contains("价格不能低于"));

        String response3 = template.getForObject(testUrl3 + "?name={1}&price={2}", String.class, "Jerry", 0.1);
        Assert.assertTrue(response3.contains("success"));

    }
}