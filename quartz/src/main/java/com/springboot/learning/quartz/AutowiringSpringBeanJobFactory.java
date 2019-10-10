package com.springboot.learning.quartz;

import com.springboot.learning.quartz.Job.TaskJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: Kevin
 * @Date: 19-4-25 上午11:51
 * @Description: 自动注入的jobBean工厂 ---> 解决quartz中job不能自动注入bean的问题
 **/
@Slf4j
@Component
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory
        implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory beanFactory;

    /**
     * 获取自动注入bean工厂
     * @param context
     */
    @Override
    public void setApplicationContext(final ApplicationContext context) {
        log.info("------ AutowiringSpringBeanJobFactory -----");
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    /**
     * 将job加入自动注入bean工厂
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        log.info("------ createJobInstance -----");
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }

}
