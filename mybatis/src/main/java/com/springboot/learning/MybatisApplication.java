package com.springboot.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:Test
 * @Description: TODO
 */
@SpringBootApplication
//@MapperScan(basePackages = "com.springboot.learning.mapper.*")
public class MybatisApplication {


    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class,args);
    }
}
