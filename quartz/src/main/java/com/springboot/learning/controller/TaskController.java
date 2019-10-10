package com.springboot.learning.controller;

import com.springboot.learning.AjaxResult;
import com.springboot.learning.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:TaskController
 * @Description: TODO
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    /**
     * <h>在这里搭建一个任务的动态管理</h>
     * 包括：
     * <p>任务创建</p>
     * <P>任务开始</P>
     * <p>任务暂停</p>
     * <p>任务恢复</p>
     * <p>任务停止</p>
     */

    @Autowired
    private TaskService taskService;

//    /**
//     * 任务开始
//     * @return
//     */
//    @RequestMapping("/startJob")
//    public AjaxResult startJob(){
//
//    }
}
