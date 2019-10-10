package com.springboot.learning.jdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:SpringTimer
 * @Description: TODO
 */
@Slf4j
public class SpringTimer {

    /**
     * spring3.0后自带定时器
     */
    @Scheduled(cron = "*/3 * * * * *")
    public void springTask(){
        log.info("----->spring 自带定时任务");
    }
}
