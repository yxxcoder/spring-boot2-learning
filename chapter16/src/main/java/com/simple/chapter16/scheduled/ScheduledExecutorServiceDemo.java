package com.simple.chapter16.scheduled;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 基于 ScheduledExecutorService 方式, 相对的比 Timer 要好
 *
 * @author yxxcoder
 * @since 2019-03-17 23:00 PM
 */
public class ScheduledExecutorServiceDemo {


    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        /**
         * 参数：
         * 1、具体执行的任务
         * 2、首次执行的延时时间
         * 3、任务执行间隔
         * 4、间隔时间单位
         */
        service.scheduleAtFixedRate(() -> System.out.println("执行任务A:" + LocalDateTime.now()), 0, 3, TimeUnit.SECONDS);
    }

}
