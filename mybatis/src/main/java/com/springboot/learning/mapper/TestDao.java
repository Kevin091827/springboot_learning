package com.springboot.learning.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Kevin
 * @Date:
 * @InterfaceName:TestDao
 * @Description: TODO
 */
@Mapper
public interface TestDao {

    //@Select({"select test from tb_test where id = #{id}"})
    String select(@Param("id") int id);
}
