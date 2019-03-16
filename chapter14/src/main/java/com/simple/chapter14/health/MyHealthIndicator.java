package com.simple.chapter14.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * 自定义健康端点
 * 实现 HealthIndicator接口，根据自己的需要判断返回的状态是 UP 还是 DOWN
 * 访问 http://localhost:8080/actuator/health 查看结果
 *
 * @author yxxcoder
 * @since 2019-03-16 21:19 PM
 */
@Component("my1")
public class MyHealthIndicator implements HealthIndicator {

    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = check();
        if (code != 0) {
            return Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        return Health.up().withDetail("code", code)
                .withDetail("version", VERSION).up().build();
    }

    private int check() {
        return new Random().nextInt(2);
    }
}
