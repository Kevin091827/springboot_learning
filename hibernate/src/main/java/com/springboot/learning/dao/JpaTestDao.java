package com.springboot.learning.dao;

import com.springboot.learning.entity.JpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JpaTestDao
 * @Description: TODO
 */
public interface JpaTestDao extends JpaRepository<JpaTest,Long>, JpaSpecificationExecutor<JpaTest> {

    /**
     *  JpaRepository<T,D>     --> 封装了基本的CRUD操作
     *        需要提供两个泛型   - T:操作的实体类类型
     *                         -  D：实体类主键的类型
     *
     *  JpaSpecificationExecutor<T>   ---> 封装了复杂查询（分页，排序等等）
     *         只需要提供一个泛型    - T:操作的实体类类型
     *
     */

    // jpql查询 --->可以用来进行复杂查询

    //支持jpql查询语句。
        //  基于注解的面向对象查询查询语句
        //  除了select * 不能直接写从from开始之外，其他都可以直接写，类似sql
        //  关于sql参数：
            // 默认按照顺序拼接
            // 也可以 ?0：表示第一个参数。?1：表示第二个参数，依次类推
    @Query(value = "from JpaTest where info = ?0")
    List<JpaTest> selectJpaTestByInfo(String info);

    @Query(value = "from JpaTest  where info = ?0 and id = ?1")
    List<JpaTest> selectJpaTestByInfoAndId(String info,int id);

    //更新（增删改）语句需要@Modifying注解配合，其他规则一致
    @Query(value = "update JpaTest set info = ?0 where id = ?1")
    @Modifying
    int updateJpaTest(String info,int id);

    //也可以编写原生sql。注解属性native = true即可

    //基于方法名称规则的查询
    //类似这种，以findBy 开头：
            // findBy + 查询比较属性 + and/or（查询方式）+查询比较属性 + and/or等等（查询方式）+查询比较属性 .....
    List<JpaTest> findByTestIdAndInfo(String testId,String info);
}
