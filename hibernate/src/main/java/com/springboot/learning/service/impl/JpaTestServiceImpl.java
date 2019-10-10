package com.springboot.learning.service.impl;

import com.springboot.learning.entity.JpaTest;
import com.springboot.learning.dao.JpaTestDao;
import com.springboot.learning.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JpaTestServiceImpl
 * @Description: TODO
 */
@Service
public class JpaTestServiceImpl implements JpaTestService {


    @Autowired
    private JpaTestDao jpaTestDao;

    /**
     * save()保存
     * @param jpaTest
     */
    @Override
    public void insert(JpaTest jpaTest) {
        jpaTest.setGmtCreate(new Date());
        jpaTest.setInfo("test1");
        //save()方法默认jpa提供得save方法当不提供id时就是执行insert操作
        jpaTestDao.save(jpaTest);
    }

    /**
     * save()更新
     * @param jpaTest
     */
    @Override
    public void update(JpaTest jpaTest) {
        jpaTest.setId((long)1);
        jpaTest.setInfo("update");
        //save()默认jpa提供的save方法当提供id时就是执行update操作
        jpaTestDao.save(jpaTest);
    }

    /**
     * delete删除
     * @param jpaTest
     */
    @Override
    public void delete(JpaTest jpaTest) {
        //jpa提供的默认delete方法默认是通过主键删除，步骤是先根据主键查看是否有这条记录，有则执行delete操作
        jpaTest.setId((long)1);
        jpaTestDao.delete(jpaTest);
    }

    /**
     * findById()根据主键查询
     */
    @Override
    public void select() {
        //findById()根据主键查询
        Optional<JpaTest> jpaTest = jpaTestDao.findById((long)2);
        System.out.println(jpaTest.toString());
        System.out.println("查询所有");
        //查询所有
        List<JpaTest> list = jpaTestDao.findAll();
        for(JpaTest j : list){
            System.out.println(j);
        }
        System.out.println("复杂查询");
        //复杂查询
        List<JpaTest> list1 = jpaTestDao.findAll(new Specification<JpaTest>() {
            /**
             * 相当于mybatis中逆向工程出来的example的条件查询,构造查询条件
             * @param root  【查询需要比较的对象属性】
             * @param criteriaQuery    自定义查询方式
             * @param criteriaBuilder  【查询条件】 查询的构造器，封装了很多查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<JpaTest> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                //获取查询需要比较的属性
                Path<Object> testId = root.get("testId");
                //构造查询方式
                    // 1.第一个参数：需要比较的属性（path对象）
                    // 2.第二个参数：该比较的属性的取值，这里可以实现动态的传参
                Predicate p1 = criteriaBuilder.equal(testId,"1");

                //多条件拼接
                Path<Object> info = root.get("info");
                //Predicate p2 = criteriaBuilder.equal(info,"1");

                //模糊查询需要注意
                    //模糊查询不同于equals，
                        // - equals在构造查询方式时直接传递path对象即可
                        // - like在构造查询方式时需要将path对象利用path.as（属性对应类型的字节码）转化才行
                Predicate p3 = criteriaBuilder.like(info.as(String.class),"1%");
                //拼接查询条件
                    //and() -- 与，是可变参数
                    //or() -- 或，也是可变参数
                Predicate p4 = criteriaBuilder.and(p1,p3);
                return p4;
            }
        });
        for(JpaTest j : list1){
            System.out.println(j);
        }
        System.out.println("复杂查询 + 排序");
        //复杂查询 + 排序
        //排序对象
            //需要传递两个参数才能实例化sort对象
            // - 第一个参数：正序还是倒序
                    // Sort.Direction.ASC：正序
                    // Sort.Direction.DESC：倒序
            // - 第二个参数：排序的属性
                    //该参数也是一个可变参数，可以有多个排序字段
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        List<JpaTest> list2 = jpaTestDao.findAll(sort);
        for(JpaTest j : list2){
            System.out.println(j);
        }
        //复杂查询 + 分页
        System.out.println("复杂查询 + 分页");
        //分页接口pageable实现类PageRequest（page，size）
            // 第一个参数：page：当前查询的页数，从0开始(即，你要查的是哪一页)
            // 第二个参数：size：每页的结果数
        Pageable pageable = new PageRequest(0,2);
        Page<JpaTest> page = jpaTestDao.findAll(pageable);
        //得到总条数
        System.out.println(page.getTotalElements());
        //得到数据集合的列表
        System.out.println(page.getContent());
        //得到总页数
        System.out.println(page.getTotalPages());
        //以上的分页和排序都可以用在findAll()方法中传递
    }
}
