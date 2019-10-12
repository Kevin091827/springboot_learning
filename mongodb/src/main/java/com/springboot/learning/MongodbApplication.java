package com.springboot.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:MongodbApplication
 * @Description: TODO
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class,args);
    }
}
