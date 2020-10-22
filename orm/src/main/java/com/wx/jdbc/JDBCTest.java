package com.wx.jdbc;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest{

    //数据源
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
//        加载资源
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("dbcp.properties");
        try {
            properties.load(in);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            System.out.println(connection.isClosed());
            System.out.println(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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



    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 11; i++) {
            Connection conn = getConnection();
            System.out.println(conn + "---" + i);
        }
    }
}
