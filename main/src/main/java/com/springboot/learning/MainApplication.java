package com.springboot.learning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:Main
 * @Description: TODO
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.springboot.learning")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

}
