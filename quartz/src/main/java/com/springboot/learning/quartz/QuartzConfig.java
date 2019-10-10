package com.springboot.learning.quartz;

import com.springboot.learning.quartz.Job.TaskJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:QuartzConfig
 * @Description: TODO
 */
@Configuration
@Slf4j
public class QuartzConfig {



    /**************************************************************************************/
    /************************************ 配置 ********************************************/
    /**************************************************************************************/

    /**
     * 配置文件配置
     * @return
     * @throws IOException
     */
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//
//        propertiesFactoryBean.setLocation(new ClassPathResource("/application.yaml"));
//
//        propertiesFactoryBean.afterPropertiesSet();
//
//        return propertiesFactoryBean.getObject();
//    }

    /**
     * 自动注入job bean工厂
     * @param applicationContext
     * @return
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        log.info("---------- jobFactory -------------");
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    /**************************************************************************************/
    /*********************************** 具体任务调度 **************************************/
    /**************************************************************************************/

    /**
     * JobDetail
     * @return
     */
    @Bean(name = "taskJobDetail")
    public JobDetailFactoryBean taskJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName("task1");
        factoryBean.setGroup("group1");
        factoryBean.setJobClass(TaskJob.class);
        return factoryBean;
    }

    /**
     * 触发器
     * @param jobDetail
     * @return
     */
    @Bean(name = "taskJobTrigger")
    public CronTriggerFactoryBean taskJobTrigger(@Qualifier("taskJobDetail")JobDetail jobDetail){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setCronExpression("0/5 * * * * ?");
        factoryBean.setGroup("group1");
        factoryBean.setName("trigger1");
        factoryBean.setStartDelay(1000L);
        factoryBean.setJobDetail(jobDetail);
        return factoryBean;
    }


    /**
     * 调度器
     * @param jobFactory
     * @param simpleJobTrigger
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory,
                                                     @Qualifier("taskJobTrigger") Trigger simpleJobTrigger){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(simpleJobTrigger);
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

}
