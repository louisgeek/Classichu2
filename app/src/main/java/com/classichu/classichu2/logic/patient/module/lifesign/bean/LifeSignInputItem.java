package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import java.io.Serializable;
import java.util.List;


public class LifeSignInputItem implements Serializable {

    public String LBH;
    //输入项号
    public String SRXH;

    //输入项名
    public String SRXM;

    //输入顺序
    public String SRSX;

    //显示标志
    public String XSBZ;

    //换行标志
    public String HHBZ;

    public List<LifeSignControlItem> LifeSignControlItemList;

}
