package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import android.util.Pair;

import java.io.Serializable;

/**
 * Created by Classichu on 2017/11/7.
 */

public class MyGroupChildBean<Child> implements Serializable {
    public static final int ITEM_TYPE_GROUP = 1;
    public static final int ITEM_TYPE_CHILD = 2;
    private int itemType;
    public Pair<String, String> stringGroupPair;
    public Child child;

    public MyGroupChildBean(Pair<String, String> stringGroupPair) {
        this.itemType = ITEM_TYPE_GROUP;
        this.stringGroupPair = stringGroupPair;
    }

    public MyGroupChildBean(Child child) {
        this.itemType = ITEM_TYPE_CHILD;
        this.child = child;
    }

    public int getItemType() {
        return itemType;
    }

}