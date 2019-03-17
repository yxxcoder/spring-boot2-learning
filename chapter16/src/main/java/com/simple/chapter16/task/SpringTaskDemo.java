package com.simple.chapter16.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 基于 Spring 自带的
 *
 * @author yxxcoder
 * @since 2019-03-17 23:00 PM
 */
@Component
public class SpringTaskDemo {

    private static final Logger log = LoggerFactory.getLogger(SpringTaskDemo.class);

    /**
     * cron：cron表达式，根据表达式循环执行
     * <code>@Async</code> 代表该任务可以进行异步工作，由原本的串行改为并行
     */
    @Async
    @Scheduled(cron = "0/1 * * * * *")
    public void scheduled1() throws InterruptedException {
        Thread.sleep(3000);
        log.info("scheduled1 每1秒执行一次：{}", LocalDateTime.now());
    }

    /**
     * fixedRate：每隔多久执行一次，无视工作时间
     */
    @Scheduled(fixedRate = 1000)
    public void scheduled2() throws InterruptedException {
        Thread.sleep(3000);
        log.info("scheduled2 每1秒执行一次：{}", LocalDateTime.now());
    }

    /**
     * fixedDelay：当前任务执行完毕后等待多久继续下次任务
     */
    @Scheduled(fixedDelay = 3000)
    public void scheduled3() throws InterruptedException {
        Thread.sleep(5000);
        log.info("scheduled3 上次执行完毕后隔3秒继续执行：{}", LocalDateTime.now());
    }

}
