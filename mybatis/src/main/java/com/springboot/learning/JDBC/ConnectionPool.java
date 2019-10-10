package com.springboot.learning.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ConnectionPool
 * @Description: TODO
 */
public class ConnectionPool {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL="";
    private static String USERNAME="";
    private static String PASSWORD="";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private LinkedList<Connection> poollist = new LinkedList<Connection>();

    //向连接池中放入10个连接对象，封装到一个集合中
    public ConnectionPool() throws Exception {
        for(int i=0;i<10;i++) {
            poollist.addLast(this.creatConnection());
        }
    }

    //创造连接对象
    public Connection creatConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //从连接池中取出一个连接对象
    public Connection getConnection() {
        return poollist.removeFirst();
    }

    //关闭连接，释放资源，将连接对象放回连接池中
    public void close(Connection conn) {
        poollist.addLast(conn);
    }
}
