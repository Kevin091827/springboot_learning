package com.springboot.learning.service;

import com.springboot.learning.entity.LogInfo;

import java.util.List;

/**
 * @Auther: Kevin
 * @Date:
 * @InterfaceName:MongodbService
 * @Description: TODO
 */
public interface LogInfoService {

    void insert(LogInfo logInfo);

    void update(String requestIp,LogInfo logInfo);

    void delete(String requestIp);

    List<LogInfo> select();


}
