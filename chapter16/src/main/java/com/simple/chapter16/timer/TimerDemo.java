package com.simple.chapter16.timer;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 基于Timer实现的定时调度（不推荐，用该方式不如用 ScheduledExecutorService ）
 *
 * @author yxxcoder
 * @since 2019-03-17 23:00 PM
 */
public class TimerDemo {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行任务:" + LocalDateTime.now());
            }
        };

        /**
         * timerTask：需要执行的任务
         * delay：延迟时间（以毫秒为单位）
         * period：间隔时间（以毫秒为单位）
         */
        new Timer().schedule(timerTask, 5000, 3000);
    }
}
