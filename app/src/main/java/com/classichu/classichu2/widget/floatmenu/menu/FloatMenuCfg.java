package com.classichu.classichu2.widget.floatmenu.menu;

public class FloatMenuCfg {
    public int mSize;
    public int mItemSize;
    public boolean mBackKeyCanHide;

    public FloatMenuCfg(int size, int itemSize) {
        this(size,itemSize,true);
    }
    public FloatMenuCfg(int size, int itemSize, boolean backKeyCanHide) {
        mSize = size;
        mItemSize = itemSize;
        mBackKeyCanHide = backKeyCanHide;
    }
}
