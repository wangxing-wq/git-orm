package com.wx.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {

    //数据源
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
//        加载资源
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("druid.properties");
        try {
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getLoginTimeout());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closed(Connection connection){
        try {
            if(connection != null || !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            Connection conn = getConnection();
            System.out.println(conn + "\t\t" + i);
        }
    }
}
