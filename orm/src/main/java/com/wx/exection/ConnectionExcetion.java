package com.wx.exection;

public class ConnectionExcetion extends RuntimeException {

    public ConnectionExcetion(){

    }

    public ConnectionExcetion(String message){
        super(message);
    }
}
