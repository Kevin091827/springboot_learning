package com.springboot.learning.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JdkTimer
 * @Description: TODO
 */
@Slf4j
public class JdkTimer {

    /**
     * Timer是jdk提供的定时器，TimeTask是定时任务，是一个抽象类
     *
     * 实现：Timer.schedule(timeTaskChild,time)//伪代码
     */

    public static Timer timer;

    public static void main(String[] args) {
        timer = new Timer();
        // 1，指定延迟执行时间执行指定任务
        timer.schedule(new TimeTaskTest1(),60);
        // 2，指定延迟时间，指定执行周期执行指定任务
        timer.schedule(new TimeTaskTest1(),10,10);
        // 3，指定时间指定周期执行指定任务
        timer.schedule(new TimeTaskTest1(),System.currentTimeMillis(),100);
        // 4, 指定时间执行指定任务
        timer.schedule(new TimeTaskTest1(),System.currentTimeMillis());
        // 5. 使用ScheduledExecutorService任务调度线程池执行定时任务
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 延时指定时间按指定执行周期执行指定任务
        scheduledExecutorService.scheduleAtFixedRate(new TimeTaskTest1(),1,1, TimeUnit.SECONDS);
        // 延时指定时间执行指定任务
        scheduledExecutorService.schedule(new TimeTaskTest1(),5,TimeUnit.SECONDS);
        //  指定延时执行时间指定时间周期执行指定任务
        scheduledExecutorService.scheduleWithFixedDelay(new TimeTaskTest1(),20,2,TimeUnit.SECONDS);
    }

    /**
     * 具体需要定时执行任务可以通过继承TimeTask，重写的run方法就是要具体定时执行的操作
     */
    public static class TimeTaskTest1 extends TimerTask {

        @Override
        public void run() {
            log.info("jdkTimer定时任务");
        }
    }
}
