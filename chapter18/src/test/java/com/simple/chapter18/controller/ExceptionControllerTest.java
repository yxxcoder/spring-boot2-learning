package com.simple.chapter18.controller;

import com.simple.chapter18.entity.ErrorResponseEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionControllerTest {

    @LocalServerPort
    private int port;

    private String testUrl1;
    private String testUrl2;
    private String testUrl3;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.testUrl1 = "http://localhost:" + port + "/test1";
        this.testUrl2 = "http://localhost:" + port + "/test2";
        this.testUrl3 = "http://localhost:" + port + "/test3";
    }

    @Test
    public void test1() {
        ResponseEntity<ErrorResponseEntity> response = template.getForEntity(testUrl1, ErrorResponseEntity.class);
        Assert.assertEquals(response.getBody().getCode(), 400);
    }

    @Test
    public void test2() {
        ResponseEntity<Map> response = template.getForEntity(testUrl2, Map.class);
        Assert.assertEquals(response.getBody().get("code"), "500");
    }

    @Test
    public void test3() {
        ResponseEntity<ErrorResponseEntity> response1 = template.getForEntity(testUrl3, ErrorResponseEntity.class);
        Assert.assertEquals(response1.getBody().getCode(), 400);

        template.getForEntity(testUrl3 + "?num={1}", String.class, "str");
    }
}