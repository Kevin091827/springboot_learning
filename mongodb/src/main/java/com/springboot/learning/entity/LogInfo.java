package com.springboot.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:LogInfo
 * @Description: TODO
 */
@Data
public class LogInfo {

    private String requestMethod;
    private String requestIp;
    private String requestUrl;
    private Map<String,String[]> parameters;
    private Object[] args;
    private Date gmtCreate;
    private String requestTime;
    private Object returnValue;
    private String errorMsg;
}
