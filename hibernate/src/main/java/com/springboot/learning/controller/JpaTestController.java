package com.springboot.learning.controller;

import com.springboot.learning.entity.JpaTest;
import com.springboot.learning.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JpaTestController
 * @Description: TODO
 */
@RestController
public class JpaTestController {

    @Autowired
    private JpaTestService jpaTestService;


    @RequestMapping("/insert")
    public void insert(){
        jpaTestService.select();
    }

}
