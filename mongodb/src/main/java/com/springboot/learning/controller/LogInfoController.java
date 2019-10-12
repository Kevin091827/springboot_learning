package com.springboot.learning.controller;

import com.springboot.learning.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:MongodbController
 * @Description: TODO
 */
@Controller
@ResponseBody
public class LogInfoController {

    @RequestMapping("/test/mongodb")
    public String test(){
        System.out.println("test");
        return "test";
    }
}
