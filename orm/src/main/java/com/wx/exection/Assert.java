package com.wx.exection;

public class Assert {

    public static void isNull(Object o){
        if (o == null){
            System.out.println("Assert -- >" + o);
        }
    }
}
