package com.wx.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {

    private static ComboPooledDataSource dataSource;

    static {
//        将指定配置文件中的 指定标签内容  加载进来
//        无参构造器时  读取默认配置
//        dataSource = new ComboPooledDataSource();
//        有参构造器  读取named-config中对应名称的配置
        dataSource = new ComboPooledDataSource("myC3p0");
        String name = dataSource.getDataSourceName();
        System.out.println("数据源的名字：" + name);
    }

    public static Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = getConn();
        if (conn != null) {
            System.out.println("成功获取连接");
        }
        closeConn(conn);
    }
}
