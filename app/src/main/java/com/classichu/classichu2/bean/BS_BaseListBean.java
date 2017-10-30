package com.classichu.classichu2.bean;

import java.util.List;

/**
 * Created by Classichu on 2017-7-17.
 */

public class BS_BaseListBean<T> {

    /**
     * ReType : 0
     * Msg : 获取机构成功!
     * Data : [{"JGID":"1","JGMC":"创业智慧医院"},{"JGID":"2","JGMC":"教工医院"},{"JGID":"999","JGMC":"中心医院"}]
     */

    private int ReType;
    private String Msg;

    public int getReType() {
        return ReType;
    }

    public String getMsg() {
        return Msg;
    }
    private List<T> Data;

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> Data) {
        this.Data = Data;
    }
}
