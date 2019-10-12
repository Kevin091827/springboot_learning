package com.springboot.learning.JDBC;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JdbcTest
 * @Description: TODO
 */
public class    ConnectFactory {

    private static String JDBC_URL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            //将加载的驱动类注册给DriverManager
            Class.forName(DRIVER);
            //获取数据库连接
            connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     * @param connection
     */
    public static void close(Connection connection){
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
