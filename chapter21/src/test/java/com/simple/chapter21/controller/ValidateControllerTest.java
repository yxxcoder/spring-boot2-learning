package com.simple.chapter21.controller;

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

    private String insertUrl;
    private String updateUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        this.insertUrl = "http://localhost:" + port + "/insert";
        this.updateUrl = "http://localhost:" + port + "/update";
    }


    @Test
    public void insert() {
        String response = template.getForObject(insertUrl + "?id={1}", String.class, "1");
        Assert.assertTrue(response.contains("name 不允许为空"));
        Assert.assertTrue(response.contains("price 不允许为空"));

        String response2 = template.getForObject(insertUrl + "?name={1}&price={2}", String.class, "Jerry", 1);
        Assert.assertTrue(response2.contains("insert"));
    }

    @Test
    public void update() {
        String response = template.getForObject(updateUrl, String.class);
        Assert.assertTrue(response.contains("id 不能为空"));
        Assert.assertTrue(response.contains("name 不允许为空"));
        Assert.assertTrue(response.contains("price 不允许为空"));

        String response2 = template.getForObject(updateUrl + "?id={1}&name={2}&price={3}", String.class, 1, "Jerry", 100);
        Assert.assertTrue(response2.contains("update"));
    }
}