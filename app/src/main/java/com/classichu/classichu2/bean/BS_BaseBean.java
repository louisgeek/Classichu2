package com.classichu.classichu2.bean;

/**
 * Created by Classichu on 2017-7-17.
 */

public class BS_BaseBean<T> {

    private int ReType;
    private String Msg;

    public int getReType() {
        return ReType;
    }

    public String getMsg() {
        return Msg;
    }

    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }
}
