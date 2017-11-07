package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Classichu on 2017/11/7.
 */

public class MyMultiItemEntity<T> implements MultiItemEntity {
    public static final int GROUP = 1;
    public static final int CHILD = 2;
    private int itemType;

    public MyMultiItemEntity(int itemType,T t) {
        this.itemType = itemType;
        this.t = t;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
    public T t;

}
