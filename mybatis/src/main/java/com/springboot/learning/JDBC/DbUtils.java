package com.springboot.learning.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:DbUtils
 * @Description: TODO
 */
public class DbUtils {


    /**
     * 增删改
     * @param sql
     * @param params
     * @param connection
     * @return
     * @throws Exception
     */
    public static int update(String sql, Object[] params, Connection connection) throws Exception {
        //连接为null，则抛出异常
        if(connection == null){
            throw new Exception("connection is null");
        }
        //操作数据库
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //封装参数
        if(null != params){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        //执行sql
        int i = preparedStatement.executeUpdate();
        return i;
    }

    /**
     * 查询
     * @param sql
     * @param params
     * @param connection
     * @return
     * @throws Exception
     */
    public static ResultSet select(String sql, Object[] params, Connection connection) throws Exception{
        //连接为null，则抛出异常
        if(connection == null){
            throw new Exception("connection is null");
        }
        //操作数据库
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //封装参数
        if(null != params){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

}
