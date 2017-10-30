package com.classichu.classichu2.bean;

import java.util.List;

/**
 * Created by Classichu on 2017/5/22.
 */

public class BaseListBean<T> {
    private int code;
    private String message;
    private List<T> dataList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
