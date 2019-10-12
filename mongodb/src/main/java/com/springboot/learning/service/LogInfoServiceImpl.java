package com.springboot.learning.service;

import com.springboot.learning.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:MongodbServiceImpl
 * @Description: TODO
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入
     * @param logInfo
     */
    @Override
    public void insert(LogInfo logInfo) {
        //插入
        mongoTemplate.save(logInfo);
        //可以使用save或者insert完成插入
        //mongoTemplate.insert(logInfo,"collection_name");
    }

    @Override
    public void update(String requestIp,LogInfo logInfo) {
        //通过where来设置update依据的属性，然后在来选择update依据属性的依据值（类似jpa）
        //  类似：gt：大于，gte：大于等于
        //      Query query1 = new Query(Criteria.where("requestIp").gt(requestIp));
        Query query = new Query(Criteria.where("requestIp").is(requestIp));
        //更新采用update对象
        //  类似sql中的set，（属性，值）
        Update update = new Update();
        update.set("gmtCreate",logInfo.getGmtCreate());
        //更新一条数据
        mongoTemplate.updateFirst(query,update,LogInfo.class);
        //更新多条数据
        mongoTemplate.updateMulti(query,update,LogInfo.class);
        //默认返回旧对象，然后更新新对象
        mongoTemplate.findAndModify(query,update,LogInfo.class);
    }

    @Override
    public void delete(String requestIp) {
        //删除和更新类似
        //通过where来设置update依据的属性，然后在来选择update依据属性的依据值（类似jpa）
        //  类似：gt：大于，gte：大于等于
        //      Query query1 = new Query(Criteria.where("requestIp").gt(requestIp));
        Query query = new Query(Criteria.where("requestIp").is(requestIp));
        //调用remove删除
        mongoTemplate.remove(query,LogInfo.class);
        //带返回值的删除
        List<LogInfo> list = mongoTemplate.findAllAndRemove(query,LogInfo.class);
    }

    /**
     * 查询全部日志
     * @return
     */
    @Override
    public List<LogInfo> select(){
        return mongoTemplate.findAll(LogInfo.class);
        //·条件查询
        //mongoTemplate.find(query,LogInfo.class);
    }
}
