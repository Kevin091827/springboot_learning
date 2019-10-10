package com.springboot.learning.service;

import com.springboot.learning.entity.JpaTest;

/**
 * @Auther: Kevin
 * @Date:
 * @InterfaceName:JpaTestService
 * @Description: TODO
 */
public interface JpaTestService {

    void insert(JpaTest jpaTest);

    void update(JpaTest jpaTest);

    void delete(JpaTest jpaTest);

    void select();
}
