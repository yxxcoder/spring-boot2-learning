package com.simple.chapter14.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * 自定义健康端点
 * 功能更加强大一点，DataSourceHealthIndicator / RedisHealthIndicator 都是这种写法
 * 访问 http://localhost:8080/actuator/health 查看结果
 *
 * @author yxxcoder
 * @since 2019-03-16 21:19 PM
 */
@Component("my2")
public class MyAbstractHealthIndicator extends AbstractHealthIndicator {

    private static final String VERSION = "v1.0.0";


    @Override
    protected void doHealthCheck(Health.Builder builder) {
        int code = check();
        if (code != 0) {
            builder.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        builder.withDetail("code", code)
                .withDetail("version", VERSION).up().build();

    }

    private int check() {
        return new Random().nextInt(2);
    }

}
