package com.wx.orm;

import com.wx.orm.annotation.Delete;
import com.wx.orm.annotation.Insert;
import com.wx.orm.annotation.Select;
import com.wx.orm.annotation.Update;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang xing
 */
public class SqlSession {

    /*
        配置文件应该是SqlSessionFactoryBuilder进行处理
        SqlSessionFactory得到创建SqlSession的一些模板配置
        两种方式,一种是读取配置文件,一种是通过代理
     */

    public boolean insert(){
        return false;
    }

    public boolean delete(){
        return false;
    }

    public boolean update(){
        return false;
    }

    public <T>T selectOne(){
        return null;
    }

    public <T> List<T> selectList(){
        return null;
    }

    @SuppressWarnings("all")
    public <T>T  getMapper(final Class classType){
        return (T)Proxy.newProxyInstance(classType.getClassLoader(), new Class[]{classType}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理方法:" + method.getName());
                Annotation annotation = method.getAnnotations()[0];
                Class clazz = annotation.annotationType();
                if(clazz == Insert.class || clazz == Delete.class || clazz == Update.class){
                    Method anootationResultValue =clazz.getMethod("value");
                    String annotationValue = (String) anootationResultValue.invoke(annotation);
                    cud(annotationValue,args);
                }else if(clazz == Select.class){

                }else{
                    System.out.println("抛出异常");
                }
                return null;
            }
        });
    }

    public boolean cud(String sql,Object ... args){
        System.out.println(sql);
        System.out.println(Arrays.toString(args));
        return false;
    }

    public <T>List<T> query(String sql,Class resultType,Object ...args){
        return null;
    }




}
