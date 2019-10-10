package com.springboot.learning.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JpaTest
 * @Description: TODO
 */

//标识数据库实体
@Entity
@Table(name = "tb_jpatest")
@Data
public class JpaTest {

    //指定主键和主键生成策略
    /**
     * jpa自带主键生成策略
     *      - TABLE : 使用一个特定的数据库表格来保存主键值
     *      - SEQUENCE : 根据底层数据库的序列来生成主键，条件是数据库支持序列。
     *                   这个值要与generator一起使用，generator 指定生成主键使用的生成器（可能是orcale中自己编写的序列）
     *      - IDENTITY :  主键由数据库自动生成（主要是支持自动增长的数据库，如mysql）
     *      - AUTO： 主键由程序控制，也是GenerationType的默认值
     */
    @Id
    //@GenericGenerator(name = "id",strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标识列属性
    @Column(name = "test_id")
    private int testId;

    @Column(name = "info",nullable = false,length = 255)
    private String info;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    //标识不是数据库的列，默认是 @Basic
    @Transient
    @Override
    public String toString() {
        return "JpaTest{" +
                "id=" + id +
                ", testId=" + testId +
                ", info='" + info + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }


}
