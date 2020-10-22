package com.wx.pool;

import com.wx.util.AbstactConnection;

import java.sql.*;

public class MyConnection extends AbstactConnection {

    private static final String DRIVER = Config.getStringValue("driver");
    private static final String URL = Config.getStringValue("url");
    private static final String USER = Config.getStringValue("user");
    private static final String PASSWORD = Config.getStringValue("password");

    private Connection conn;
    private boolean state;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyConnection(){
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    @Override
    public void commit() throws SQLException {
        conn.commit();
    }

    @Override
    public void close() throws SQLException {
        setState(false);
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return conn.getMetaData();
    }
}
