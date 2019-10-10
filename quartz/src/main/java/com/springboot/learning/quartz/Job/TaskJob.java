package com.springboot.learning.quartz.Job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:TaskJob
 * @Description: TODO
 */
@Slf4j
@Component
@EnableScheduling
public class TaskJob implements Job {

    /**
     * 具体需要完成的任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("quartz定时任务");
    }
}
