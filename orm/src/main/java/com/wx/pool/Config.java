package com.wx.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

    private static Map<String,String> configProperties = new HashMap<String, String>();

    static {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("config.properties");
//        读取配置文件
            Properties properties = new Properties();
            properties.load(in);
            Enumeration e = properties.propertyNames();
            while (e.hasMoreElements()){
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                configProperties.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String key){
        return configProperties.get(key);
    }

    public static Integer getIntegerValue(String key){
        return Integer.valueOf(configProperties.get(key));
    }
}
