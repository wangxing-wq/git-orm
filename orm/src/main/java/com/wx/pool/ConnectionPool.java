package com.wx.pool;

import com.wx.exection.ConnectionExcetion;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang xing
 */
public class ConnectionPool {

    private static List<Connection> connectionPool = new ArrayList();
    private static Integer CONNECTION_COUNT = Config.getIntegerValue("count");
    private static Integer CONNECTION_TIME = Config.getIntegerValue("time");
    private static ConnectionPool pool = null;
    static {
        if(CONNECTION_COUNT == null || CONNECTION_COUNT == 0){
            CONNECTION_COUNT = 10;
        }
        if(CONNECTION_TIME == null || CONNECTION_TIME == 0){
            CONNECTION_TIME = 5000;
        }
        for (int i = 0; i < CONNECTION_COUNT; i++) {
            connectionPool.add(new MyConnection());
        }
    }
    private ConnectionPool(){}

    public static ConnectionPool getInstance(){
        if(pool == null){
            synchronized (ConnectionPool.class){
                if (pool == null){
                    pool = new ConnectionPool();
                }
            }
        }
        return pool;
    }

    private Connection getUsableConnection(){
        Connection conn = null;
        for (int i = 0; i < connectionPool.size(); i++) {
            MyConnection myConnection = (MyConnection) connectionPool.get(i);
            synchronized (myConnection){
                if(!myConnection.isState()){
                    conn = myConnection;
                    myConnection.setState(true);
                    break;
                }
            }
        }
        return conn;
    }

    public Connection getConnection(){
        Connection conn = getUsableConnection();
        int count = 0;
//        循环条件 conn == null 且 coun < CONNECTION_TIME/100
        while (conn == null && count < CONNECTION_TIME/100){
            conn = getUsableConnection();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count += 1;
        }
        if(conn == null){
            throw new ConnectionExcetion("连接时间过长,请重新连接");
        }
        return conn;
    }


}
