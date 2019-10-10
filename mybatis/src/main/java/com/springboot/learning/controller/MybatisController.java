package com.springboot.learning.controller;

import com.springboot.learning.mapper.TestDao;
import com.springboot.learning.service.MyBatisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:    基于mybatis实现简单的图书管理系统
 * @Author:         Kevin
 * @CreateDate:     2019/10/2 18:35
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/10/2 18:35
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@RestController
@Slf4j
public class MybatisController {

    @Autowired
    private MyBatisService myBatisService;


    /**
     * <h>基于mybatis实现简单的图书管理系统</h>
     * <li>主要目标</li>
     * <li>1,熟悉mybatis框架的使用</li>
     * <li>2,熟悉利用mybatis实现增删改查</li>
     * <li>3,熟悉逆向工程</li>
     */

    @Autowired
    private TestDao testDao;

    @RequestMapping("/test")
    public void test(){
        System.out.println(testDao.select(1));
    }

}
